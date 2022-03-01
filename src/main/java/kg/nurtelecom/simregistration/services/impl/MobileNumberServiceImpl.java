package kg.nurtelecom.simregistration.services.impl;

import kg.nurtelecom.simregistration.entities.MobileNumberEntity;
import kg.nurtelecom.simregistration.exceptions.MobileNumberAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.MobileNumberNotFoundException;
import kg.nurtelecom.simregistration.models.MobileNumberModel;
import kg.nurtelecom.simregistration.repositories.MobileNumberRepository;
import kg.nurtelecom.simregistration.services.MobileNumberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileNumberServiceImpl implements MobileNumberService {

    private MobileNumberRepository repository;

    public MobileNumberServiceImpl(MobileNumberRepository repository) {
        this.repository = repository;
    }

    @Override
    public MobileNumberModel getMobileNumberById(Long id) throws MobileNumberNotFoundException {
        MobileNumberEntity mobileNumberEntity;
        if(!repository.existsById(id)) {
            throw new MobileNumberNotFoundException("MobileNumberNotFoundException");
        } else {
            mobileNumberEntity = repository.getById(id);
            return toModel(mobileNumberEntity);
        }
    }

    @Override
    public MobileNumberModel getByMobileNumber(String number) {
        MobileNumberEntity entity = repository.findByMobileNumber(number);
        if(entity == null) {
            throw new MobileNumberNotFoundException("MobileNumberNotFoundException");
        } else {
            return toModel(entity);
        }
    }

    @Override
    public List<MobileNumberModel> getAllMobileNumbers() {
        return map(repository.findAll());
    }

    @Override
    public MobileNumberModel saveMobileNumber(MobileNumberModel model) {
        MobileNumberEntity entity = toEntity(model);
        if(repository.findByMobileNumber(entity.getNumber()) == null) {
            repository.save(entity);
            entity = repository.findByMobileNumber(model.getNumber());
            return toModel(entity);
        } else {
            throw new MobileNumberAlreadyExistsException("Exists");
        }
    }

    @Override
    public MobileNumberModel deleteMobileNumberById(Long id) {
        MobileNumberModel model;
        if(repository.existsById(id)) {
            model = toModel(repository.getById(id));
            repository.deleteById(id);
            return model;
        } else {
            throw new MobileNumberNotFoundException("Not found");
        }
    }

    @Override
    public MobileNumberModel updateMobileNumberById(Long id, MobileNumberModel model) {
        if(repository.existsById(id)) {
            MobileNumberEntity entity = repository.getById(id);
            entity.setNumber(model.getNumber());
            repository.save(entity);
            return toModel(entity);
        } else {
            throw new MobileNumberNotFoundException("Not found");
        }
    }

    // convert Entity to Model
    public static MobileNumberModel toModel(MobileNumberEntity entity) {
        MobileNumberModel model = new MobileNumberModel();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setStatus(entity.getStatus());
        return model;
    }

    // convert Model to Entity
    public static MobileNumberEntity toEntity(MobileNumberModel model) {
        MobileNumberEntity entity = new MobileNumberEntity();
        entity.setNumber(model.getNumber());
        entity.setStatus(model.getStatus());
        return entity;
    }

    public List<MobileNumberModel> map(List<MobileNumberEntity> numbers) {
        if (numbers == null) {
            return null;
        }

        List<MobileNumberModel> list = new ArrayList<MobileNumberModel>(numbers.size());
        for (MobileNumberEntity entity : numbers) {
            list.add(toModel(entity));
        }

        return list;
    }

}
