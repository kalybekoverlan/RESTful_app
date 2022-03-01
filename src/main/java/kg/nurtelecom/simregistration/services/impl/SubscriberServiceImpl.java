package kg.nurtelecom.simregistration.services.impl;

import kg.nurtelecom.simregistration.entities.NationalityEntity;
import kg.nurtelecom.simregistration.entities.SubscriberEntity;
import kg.nurtelecom.simregistration.exceptions.SubscriberAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.SubscriberNotFoundException;
import kg.nurtelecom.simregistration.models.NationalityModel;
import kg.nurtelecom.simregistration.models.SubscriberModel;
import kg.nurtelecom.simregistration.repositories.SubscriberRepository;
import kg.nurtelecom.simregistration.services.SubscriberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private SubscriberRepository repository;

    public SubscriberServiceImpl(SubscriberRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubscriberModel getSubscriberById(Long id) throws SubscriberNotFoundException {
        SubscriberEntity entity;
        if(!repository.existsById(id)) {
            throw new SubscriberNotFoundException("SubscriberNotFoundException");
        } else {
            entity = repository.getById(id);
            return toModel(entity);
        }
    }

    @Override
    public List<SubscriberModel> getAllSubscribers() {
        return map(repository.findAll());
    }

    @Override
    public SubscriberModel saveSubscriber(SubscriberModel model) throws SubscriberAlreadyExistsException {
        SubscriberEntity entity = toEntity(model);
        if(repository.findByPersonalNumber(entity.getPersonalNumber()) == null) {
            repository.save(entity);
            entity = repository.findByPersonalNumber(model.getPersonalNumber());
            return toModel(entity);
        } else {
            throw new SubscriberAlreadyExistsException("Exists");
        }
    }

    @Override
    public SubscriberModel deleteSubscriberById(Long id) throws SubscriberNotFoundException {
        SubscriberModel model;
        if(repository.existsById(id)) {
            model = toModel(repository.getById(id));
            repository.deleteById(id);
            return model;
        } else {
            throw new SubscriberNotFoundException("SubscriberNotFoundException");
        }
    }

    @Override
    public SubscriberModel updateSubscriberById(Long id, SubscriberModel model) throws SubscriberNotFoundException {
        if(repository.existsById(id)) {
            SubscriberEntity entity = toEntity(model);
            entity.setId(id);
            repository.save(entity);
            return toModel(entity);
        } else {
            throw new SubscriberNotFoundException("SubscriberNotFoundException");
        }
    }

    // convert Entity to Model
    public static SubscriberModel toModel(SubscriberEntity entity) {
        SubscriberModel model = new SubscriberModel();

        NationalityModel nationModel = NationalityServiceImpl.toModel(entity.getNationality());

        model.setId(entity.getId());
        model.setCreatedOn(entity.getCreatedOn());
        model.setSurname(entity.getSurname());
        model.setName(entity.getName());
        model.setPatronymic(entity.getPatronymic());
        model.setGender(entity.getGender());
        model.setNation(nationModel);
        model.setDocType(entity.getDocumentType());
        model.setDocumentNumber(entity.getDocumentNumber());
        model.setPersonalNumber(entity.getPersonalNumber());
        model.setDateOfBirth(entity.getDateOfBirth());

        return model;
    }

    // convert Model to Entity
    public static SubscriberEntity toEntity(SubscriberModel model) {
        SubscriberEntity entity = new SubscriberEntity();
        entity.setSurname(model.getSurname());
        entity.setName(model.getName());
        entity.setPatronymic(model.getPatronymic());
        entity.setGender(model.getGender());
        entity.setNationality(new NationalityEntity(model.getNation().getId()));
        entity.setDocumentType(model.getDocType());
        entity.setDocumentNumber(model.getDocumentNumber());
        entity.setPersonalNumber(model.getPersonalNumber());
        entity.setDateOfBirth(model.getDateOfBirth());
        entity.setPlaceOfBirth(model.getPlaceOfBirth());

        return entity;
    }

    public List<SubscriberModel> map(List<SubscriberEntity> subscribers) {
        if (subscribers == null)
            return null;

        List<SubscriberModel> list = new ArrayList<SubscriberModel>(subscribers.size());
        for (SubscriberEntity entity : subscribers) {
            list.add(toModel(entity));
        }

        return list;
    }
}
