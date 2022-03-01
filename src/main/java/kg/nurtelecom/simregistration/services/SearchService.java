package kg.nurtelecom.simregistration.services;

import kg.nurtelecom.simregistration.models.AgreementModel;
import kg.nurtelecom.simregistration.models.SearchModel;

import java.util.List;

public interface SearchService {
    public List<AgreementModel> searchByFields(SearchModel model);
}
