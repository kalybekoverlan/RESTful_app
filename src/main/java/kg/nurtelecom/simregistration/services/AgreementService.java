package kg.nurtelecom.simregistration.services;

import kg.nurtelecom.simregistration.exceptions.AgreementAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.AgreementNotFoundException;
import kg.nurtelecom.simregistration.models.AgreementModel;

import java.util.List;

public interface AgreementService {

    AgreementModel getAgreementById(Long id) throws AgreementNotFoundException;

    List<AgreementModel> getAllAgreements();

    AgreementModel saveAgreement(AgreementModel model) throws AgreementAlreadyExistsException;

    AgreementModel deleteAgreementById(Long id) throws AgreementNotFoundException;

    AgreementModel updateAgreementById(Long id, AgreementModel model) throws AgreementNotFoundException;
}
