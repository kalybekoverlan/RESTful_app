package kg.nurtelecom.simregistration.services.impl;

import kg.nurtelecom.simregistration.entities.AgreementEntity;
import kg.nurtelecom.simregistration.entities.MobileNumberEntity;
import kg.nurtelecom.simregistration.entities.SubscriberEntity;
import kg.nurtelecom.simregistration.exceptions.AgreementAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.AgreementNotFoundException;
import kg.nurtelecom.simregistration.models.AgreementModel;
import kg.nurtelecom.simregistration.models.MobileNumberModel;
import kg.nurtelecom.simregistration.models.SubscriberModel;
import kg.nurtelecom.simregistration.repositories.AgreementRepository;
import kg.nurtelecom.simregistration.services.AgreementService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {

    private AgreementRepository repository;

    public AgreementServiceImpl(AgreementRepository repository) {
        this.repository = repository;
    }

    @Override
    public AgreementModel getAgreementById(Long id) throws AgreementNotFoundException {
        if(repository.existsById(id)) {
            AgreementEntity entity = repository.getById(id);
            return toModel(entity);
        } else {
            throw new AgreementNotFoundException("NotFound");
        }
    }

    @Override
    public List<AgreementModel> getAllAgreements() {
        return map(repository.findAll());
    }

    @Override
    public AgreementModel saveAgreement(AgreementModel model) throws AgreementAlreadyExistsException {
        AgreementEntity entity = toEntity(model);
        if(repository.findByPhoneAndSubscriber(model.getMobileNumberModel().getId(), model.getSubscriber().getId() ) == null) {
            repository.save(entity);
            entity = repository.findByPhoneAndSubscriber(model.getMobileNumberModel().getId(), model.getSubscriber().getId() );
            return toModel(entity);
        } else
            throw new AgreementAlreadyExistsException("AlreadyExists");
    }

    @Override
    public AgreementModel deleteAgreementById(Long id) throws AgreementNotFoundException {
        AgreementModel model;
        if(repository.existsById(id)) {
            model = toModel(repository.getById(id));
            repository.deleteById(id);
            return model;
        } else {
            throw new AgreementNotFoundException("NotFoundException");
        }
    }

    @Override
    public AgreementModel updateAgreementById(Long id, AgreementModel model) throws AgreementNotFoundException {
        if(repository.existsById(id)) {
            AgreementEntity entity = toEntity(model);
            entity.setId(model.getId());
            repository.save(entity);
            return toModel(entity);
        } else {
            throw new AgreementNotFoundException("NotFound");
        }
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

    // convert Model to Entity
    public static AgreementEntity toEntity(AgreementModel model) {
        AgreementEntity entity = new AgreementEntity();
        entity.setSubscriber(new SubscriberEntity(model.getSubscriber().getId()));
        entity.setMobileNumber(new MobileNumberEntity(model.getMobileNumberModel().getId()));
        entity.setStatus(model.getStatus());

        return entity;
    }

    //List of Entities to list of Models
    public List<AgreementModel> map(List<AgreementEntity> agreements) {
        if (agreements == null)
            return null;

        List<AgreementModel> list = new ArrayList<AgreementModel>(agreements.size());
        for (AgreementEntity entity : agreements) {
            list.add(toModel(entity));
        }

        return list;
    }
}
