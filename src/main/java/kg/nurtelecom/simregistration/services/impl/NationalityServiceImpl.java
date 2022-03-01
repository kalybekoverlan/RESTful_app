package kg.nurtelecom.simregistration.services.impl;

import kg.nurtelecom.simregistration.entities.NationalityEntity;
import kg.nurtelecom.simregistration.exceptions.NationalityAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.NationalityNotFoundException;
import kg.nurtelecom.simregistration.models.NationalityModel;
import kg.nurtelecom.simregistration.repositories.NationalityRepository;
import kg.nurtelecom.simregistration.services.NationalityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NationalityServiceImpl implements NationalityService {

    private NationalityRepository repository;

    public NationalityServiceImpl(NationalityRepository repository) {
        this.repository = repository;
    }

    @Override
    public NationalityModel getNationalityById(Long id) throws NationalityNotFoundException {
        NationalityEntity entity;
        if(repository.existsById(id)) {
            entity = repository.getById(id);
            return toModel(entity);
        } else {
            throw new NationalityNotFoundException("Not Found Exception");
        }
    }

    @Override
    public NationalityModel getByNationalityName(String name) throws NationalityNotFoundException {
        NationalityEntity entity = repository.findByNationalityName(name);
        if(entity == null) {
            throw new NationalityNotFoundException("Not Found Exception");
        } else {
            return toModel(entity);
        }
    }

    @Override
    public List<NationalityModel> getAllNationalities() {
        return map(repository.findAll());
    }

    @Override
    public NationalityModel saveNationality(NationalityModel model) throws NationalityAlreadyExistsException {
        NationalityEntity entity = toEntity(model);
        if(repository.findByNationalityName(entity.getNationalityName()) == null) {
            repository.save(entity);
            entity = repository.findByNationalityName(model.getNationalityName());
            return toModel(entity);
        } else {
            throw new NationalityAlreadyExistsException("Exists");
        }
    }

    @Override
    public NationalityModel deleteNationalityById(Long id) throws NationalityNotFoundException {
        NationalityModel model;
        if(repository.existsById(id)) {
            model = toModel(repository.getById(id));
            repository.deleteById(id);
            return model;
        } else {
            throw new NationalityNotFoundException("Not found");
        }
    }

    @Override
    public NationalityModel updateNationalityById(Long id, NationalityModel model) throws NationalityNotFoundException {
        if(repository.existsById(id)) {
            NationalityEntity entity = repository.getById(id);
            entity.setNationalityName(model.getNationalityName());
            repository.save(entity);
            return toModel(entity);
        } else {
            throw new NationalityNotFoundException("Not found");
        }
    }

    // convert Entity to Model
    public static NationalityModel toModel(NationalityEntity entity) {
        if(entity == null)
            return null;
        NationalityModel model = new NationalityModel();
        model.setId(entity.getId());
        model.setNationalityName(entity.getNationalityName());
        return model;
    }

    // convert Model to Entity
    public static NationalityEntity toEntity(NationalityModel model) {
        NationalityEntity entity = new NationalityEntity();
        entity.setNationalityName(model.getNationalityName());
        return entity;
    }

    public List<NationalityModel> map(List<NationalityEntity> nationalities) {
        if (nationalities == null) {
            return null;
        }

        List<NationalityModel> list = new ArrayList<NationalityModel>(nationalities.size());
        for (NationalityEntity entity : nationalities) {
            list.add(toModel(entity));
        }

        return list;
    }


}
