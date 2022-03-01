package kg.nurtelecom.simregistration.controllers;

import kg.nurtelecom.simregistration.enums.ResultCode;
import kg.nurtelecom.simregistration.models.AgreementModel;
import kg.nurtelecom.simregistration.models.ResponseMessage;
import kg.nurtelecom.simregistration.models.SearchModel;
import kg.nurtelecom.simregistration.services.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private SearchService service;

    public SearchController(SearchService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseMessage< List<AgreementModel> > search(@RequestBody SearchModel model) {
        List<AgreementModel> resulList = service.searchByFields(model);
        return  new ResponseMessage< List<AgreementModel> >(resulList, ResultCode.SUCCESS, "SEARCHED");
    }
}
