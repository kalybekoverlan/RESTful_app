package kg.nurtelecom.simregistration.controllers;

import kg.nurtelecom.simregistration.enums.ResultCode;
import kg.nurtelecom.simregistration.exceptions.MobileNumberAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.MobileNumberNotFoundException;
import kg.nurtelecom.simregistration.models.MobileNumberModel;
import kg.nurtelecom.simregistration.models.ResponseMessage;
import kg.nurtelecom.simregistration.services.MobileNumberService;
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
@RequestMapping(path = "/api/v1/mobilenumbers")
public class MobileNumberController {

    private MobileNumberService service;

    public MobileNumberController(MobileNumberService service) {
        this.service = service;
    }

    //create
    @PostMapping("/add")
    public ResponseMessage<MobileNumberModel>  create(@RequestBody MobileNumberModel model) {
        try{
            model = service.saveMobileNumber(model);
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.SUCCESS, "CREATED");
        } catch (MobileNumberAlreadyExistsException e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION, "ALREADY EXISTS EXCEPTION");
        }
    }

    //read
    @GetMapping
    public ResponseMessage<List<MobileNumberModel>> getAll() {
        List<MobileNumberModel> resulList = service.getAllMobileNumbers();
        return new ResponseMessage<List<MobileNumberModel>>(resulList, ResultCode.SUCCESS, "READED");
    }

    //read
    @GetMapping("/bynumber/{number}")
    public ResponseMessage<MobileNumberModel> read(@PathVariable("number") String number) {
        MobileNumberModel model = null;
        try {
            model = service.getByMobileNumber(number);
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.SUCCESS, "READED");
        } catch (MobileNumberNotFoundException e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION, "NOT FOUND EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        }
    }

    //read
    @GetMapping("/{id}")
    public ResponseMessage<MobileNumberModel> getOne(@PathVariable("id") Long id) {
        MobileNumberModel model = null;
        try {
            model = service.getMobileNumberById(id);
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.SUCCESS, "SUCCESS");
        } catch (MobileNumberNotFoundException e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        }
    }

    //delele
    @DeleteMapping(value = "/delete/{id}")
    public ResponseMessage<MobileNumberModel> delete(@PathVariable("id") Long id) {
        MobileNumberModel model = null;
        try {
            model = service.deleteMobileNumberById(id);
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.SUCCESS, "Deleted");
        } catch (MobileNumberNotFoundException e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION, "Exception");
        }
    }

    //update
    @PutMapping(value = "/update/{id}")
    public ResponseMessage<MobileNumberModel> update(@PathVariable("id") Long id, @RequestBody MobileNumberModel model) {
        try{
            model = service.updateMobileNumberById(id, model);
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.SUCCESS, "Updated");
        } catch (MobileNumberNotFoundException e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION,"Not found Exception");
        } catch (MobileNumberAlreadyExistsException e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION,"Exist Exception");
        }catch (Exception e) {
            return new ResponseMessage<MobileNumberModel>(model, ResultCode.EXCEPTION,"Exception");
        }
    }
}
