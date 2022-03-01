package kg.nurtelecom.simregistration.services.impl;

import kg.nurtelecom.simregistration.entities.AgreementEntity;
import kg.nurtelecom.simregistration.models.AgreementModel;
import kg.nurtelecom.simregistration.models.MobileNumberModel;
import kg.nurtelecom.simregistration.models.SearchModel;
import kg.nurtelecom.simregistration.models.SubscriberModel;
import kg.nurtelecom.simregistration.repositories.AgreementRepository;
import kg.nurtelecom.simregistration.services.SearchService;
import kg.nurtelecom.simregistration.specification.SearchSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private AgreementRepository repository;

    public SearchServiceImpl(AgreementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AgreementModel> searchByFields(SearchModel model) {
        Specification<AgreementEntity> spec = new SearchSpecification(model);
        return map(repository.findAll(spec));
    }

    // convert Entity to Model
    public static AgreementModel toModel(AgreementEntity entity) {
        AgreementModel model = new AgreementModel();
        SubscriberModel subscriber = SubscriberServiceImpl.toModel(entity.getSubscriber());
        MobileNumberModel numberModel = MobileNumberServiceImpl.toModel(entity.getMobileNumber());

        model.setId(entity.getId());
        model.setCreatedOn(entity.getCreatedOn());
        model.setSubscriber(subscriber);
        model.setMobileNumberModel(numberModel);
        model.setStatus(entity.getStatus());

        return model;
    }

    //List of Entities to list of Models
    public List<AgreementModel> map(List<AgreementEntity> resultListEntity) {
        if (resultListEntity == null)
            return null;

        List<AgreementModel> list = new ArrayList<AgreementModel>(resultListEntity.size());
        for (AgreementEntity entity : resultListEntity) {
            list.add(toModel(entity));
        }

        return list;
    }

}
