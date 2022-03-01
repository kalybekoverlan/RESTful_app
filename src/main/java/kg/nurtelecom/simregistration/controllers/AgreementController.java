package kg.nurtelecom.simregistration.controllers;

import kg.nurtelecom.simregistration.enums.ResultCode;
import kg.nurtelecom.simregistration.exceptions.AgreementAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.AgreementNotFoundException;
import kg.nurtelecom.simregistration.exceptions.MobileNumberNotFoundException;
import kg.nurtelecom.simregistration.models.AgreementModel;
import kg.nurtelecom.simregistration.models.ResponseMessage;
import kg.nurtelecom.simregistration.services.AgreementService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/agreements")
public class AgreementController {

    private AgreementService service;

    public AgreementController(AgreementService service) {
        this.service = service;
    }

    //create
    @PostMapping("/add")
    public ResponseMessage<AgreementModel> create(@RequestBody AgreementModel model) {
        try{
            model = service.saveAgreement(model);
            return new ResponseMessage<AgreementModel>(model, ResultCode.SUCCESS, "CREATED");
        } catch (AgreementAlreadyExistsException e) {
            return new ResponseMessage<AgreementModel>(model, ResultCode.EXCEPTION, "ALREADY EXISTS EXCEPTION");
        }
    }

    //read all
    @GetMapping
    public ResponseMessage<List<AgreementModel>> readAll() {
        List<AgreementModel> resulList = service.getAllAgreements();
        return new ResponseMessage<List<AgreementModel>>(resulList, ResultCode.SUCCESS, "READED");
    }

    //read one
    @GetMapping("/{id}")
    public ResponseMessage<AgreementModel> readOne(@PathVariable("id") Long id) {
        AgreementModel model = null;
        try {
            model = service.getAgreementById(id);
            return new ResponseMessage<AgreementModel>(model, ResultCode.SUCCESS, "READED");
        } catch (AgreementNotFoundException e) {
            return new ResponseMessage<AgreementModel>(model, ResultCode.EXCEPTION, "NOT FOUND EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<AgreementModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        }
    }

    //update
    @PutMapping(value = "/update/{id}")
    public ResponseMessage<AgreementModel> update(@PathVariable("id") Long id, @RequestBody AgreementModel model) {
        try{
            model = service.updateAgreementById(id, model);
            return new ResponseMessage<AgreementModel>(model, ResultCode.SUCCESS, "Updated");
        } catch (AgreementNotFoundException e) {
            return new ResponseMessage<AgreementModel>(model, ResultCode.EXCEPTION,"Not found Exception");
        } catch (Exception e) {
            return new ResponseMessage<AgreementModel>(model, ResultCode.EXCEPTION,"Exception");
        }
    }


    //delele
    @DeleteMapping(value = "/delete/{id}")
    public ResponseMessage<AgreementModel> delete(@PathVariable("id") Long id) {
        AgreementModel model = null;
        try {
            model = service.deleteAgreementById(id);
            return new ResponseMessage<AgreementModel>(model, ResultCode.SUCCESS, "Deleted");
        } catch (MobileNumberNotFoundException e) {
            return new ResponseMessage<AgreementModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<AgreementModel>(model, ResultCode.EXCEPTION, "Exception");
        }
    }
}
