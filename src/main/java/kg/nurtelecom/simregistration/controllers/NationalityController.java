package kg.nurtelecom.simregistration.controllers;

import kg.nurtelecom.simregistration.enums.ResultCode;
import kg.nurtelecom.simregistration.exceptions.NationalityAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.NationalityNotFoundException;
import kg.nurtelecom.simregistration.models.NationalityModel;
import kg.nurtelecom.simregistration.models.ResponseMessage;
import kg.nurtelecom.simregistration.services.NationalityService;
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
@RequestMapping(path = "/api/v1/nationalities")
public class NationalityController {

    private NationalityService service;

    public NationalityController(NationalityService service) {
        this.service = service;
    }

    //create
    @PostMapping("/add")
    public ResponseMessage<NationalityModel>  create(@RequestBody NationalityModel model) {
        try{
            model = service.saveNationality(model);
            return new ResponseMessage<NationalityModel>(model, ResultCode.SUCCESS, "CREATED");
        } catch (NationalityAlreadyExistsException e) {
            return new ResponseMessage<NationalityModel>(model, ResultCode.EXCEPTION, "ALREADY EXISTS EXCEPTION");
        }
    }

    //read
    @GetMapping
    public ResponseMessage< List<NationalityModel> > getAll() {
        List<NationalityModel> resulList = service.getAllNationalities();
        return new ResponseMessage<List<NationalityModel>>(resulList, ResultCode.SUCCESS, "READED");
    }

    //read
    @GetMapping("/byname/{name}")
    public ResponseMessage<NationalityModel> read(@PathVariable("name") String name) {
        NationalityModel model = null;
        try {
            model = service.getByNationalityName(name);
            return new ResponseMessage<NationalityModel>(model, ResultCode.SUCCESS, "READED");
        } catch (NationalityNotFoundException e) {
            return new ResponseMessage<NationalityModel>(model, ResultCode.EXCEPTION, "NOT FOUND EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<NationalityModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        }
    }

    //read
    @GetMapping("/{id}")
    public ResponseMessage<NationalityModel> getOne(@PathVariable("id") Long id) {
        NationalityModel model = null;
        try {
            model = service.getNationalityById(id);
            return new ResponseMessage<NationalityModel>(model, ResultCode.SUCCESS, "SUCCESS");
        } catch (NationalityNotFoundException e) {
            return new ResponseMessage<NationalityModel>(model, ResultCode.EXCEPTION, "NOT FOUND EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<NationalityModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        }
    }

    //delele
    @DeleteMapping(value = "/delete/{id}")
    public ResponseMessage<NationalityModel> delete(@PathVariable("id") Long id) {
        NationalityModel model = null;
        try {
            model = service.deleteNationalityById(id);
            return new ResponseMessage<NationalityModel>(model, ResultCode.SUCCESS, "Deleted");
        } catch (NationalityNotFoundException e) {
            return new ResponseMessage<NationalityModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<NationalityModel>(model, ResultCode.EXCEPTION, "Exception");
        }
    }

    //update
    @PutMapping(value = "/update/{id}")
    public ResponseMessage<NationalityModel> update(@PathVariable("id") Long id, @RequestBody NationalityModel model) {
        try{
            model = service.updateNationalityById(id, model);
            return new ResponseMessage<NationalityModel>(model, ResultCode.SUCCESS, "Updated");
        } catch (NationalityNotFoundException e) {
            return new ResponseMessage<NationalityModel>(model, ResultCode.EXCEPTION,"Not found Exception");
        } catch (Exception e) {
            return new ResponseMessage<NationalityModel>(model, ResultCode.EXCEPTION,"Exception");
        }
    }

}
