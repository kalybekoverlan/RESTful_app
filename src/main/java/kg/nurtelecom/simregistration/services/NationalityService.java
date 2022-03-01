package kg.nurtelecom.simregistration.services;

import kg.nurtelecom.simregistration.exceptions.NationalityAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.NationalityNotFoundException;
import kg.nurtelecom.simregistration.models.NationalityModel;

import java.util.List;

public interface NationalityService {

    NationalityModel getNationalityById(Long id) throws NationalityNotFoundException;

    NationalityModel getByNationalityName(String name)  throws NationalityNotFoundException;

    List<NationalityModel> getAllNationalities();

    NationalityModel saveNationality(NationalityModel model) throws NationalityAlreadyExistsException;

    NationalityModel deleteNationalityById(Long id) throws NationalityNotFoundException;

    NationalityModel updateNationalityById(Long id, NationalityModel model) throws NationalityNotFoundException;
}
