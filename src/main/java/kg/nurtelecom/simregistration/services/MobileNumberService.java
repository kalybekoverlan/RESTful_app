package kg.nurtelecom.simregistration.services;

import kg.nurtelecom.simregistration.models.MobileNumberModel;

import java.util.List;

public interface MobileNumberService{

    MobileNumberModel getMobileNumberById(Long id);

    MobileNumberModel getByMobileNumber(String number);

    List<MobileNumberModel> getAllMobileNumbers();

    MobileNumberModel saveMobileNumber(MobileNumberModel mobileNumberModel);

    MobileNumberModel deleteMobileNumberById(Long id);

    MobileNumberModel updateMobileNumberById(Long id, MobileNumberModel mobileNumberModel);
}
