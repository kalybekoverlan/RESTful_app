package kg.nurtelecom.simregistration.controllers;

import kg.nurtelecom.simregistration.enums.ResultCode;
import kg.nurtelecom.simregistration.exceptions.SubscriberAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.SubscriberNotFoundException;
import kg.nurtelecom.simregistration.models.ResponseMessage;
import kg.nurtelecom.simregistration.models.SubscriberModel;
import kg.nurtelecom.simregistration.services.SubscriberService;
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
@RequestMapping(path = "/api/v1/subscribers")
public class SubscriberController {

    private SubscriberService service;

    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseMessage<SubscriberModel> create(@RequestBody SubscriberModel model) {
        try{
            model = service.saveSubscriber(model);
            return new ResponseMessage<SubscriberModel>(model, ResultCode.SUCCESS, "created");
        }  catch (SubscriberAlreadyExistsException e) {
            return new ResponseMessage<SubscriberModel>(model, ResultCode.EXCEPTION, e.toString());
        }
    }

    @GetMapping
    public ResponseMessage< List<SubscriberModel> > readAll() {
        List<SubscriberModel> resulList = service.getAllSubscribers();
        return new ResponseMessage<List<SubscriberModel>>(resulList, ResultCode.SUCCESS, "READED");
    }

    @GetMapping("/{id}")
    public ResponseMessage<SubscriberModel> read(@PathVariable("id") Long id) {
        SubscriberModel model = null;
        try {
            model = service.getSubscriberById(id);
            return new ResponseMessage<SubscriberModel>(model, ResultCode.SUCCESS, "SUCCESS");
        } catch (SubscriberNotFoundException e) {
            return new ResponseMessage<SubscriberModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<SubscriberModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseMessage<SubscriberModel> update(@PathVariable("id") Long id,@RequestBody SubscriberModel model) {
        try{
            model = service.updateSubscriberById(id, model);
            return new ResponseMessage<SubscriberModel>(model, ResultCode.SUCCESS, "Updated");
        } catch (SubscriberNotFoundException e) {
            return new ResponseMessage<SubscriberModel>(model, ResultCode.EXCEPTION,"Not found Exception");
        } catch (Exception e) {
            return new ResponseMessage<SubscriberModel>(model, ResultCode.EXCEPTION,"Exception");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<SubscriberModel> delete(@PathVariable("id") Long id) {
        SubscriberModel model = null;
        try {
            model = service.deleteSubscriberById(id);
            return new ResponseMessage<SubscriberModel>(model, ResultCode.SUCCESS, "Deleted");
        } catch (SubscriberNotFoundException e) {
            return new ResponseMessage<SubscriberModel>(model, ResultCode.EXCEPTION, "EXCEPTION");
        } catch (Exception e) {
            return new ResponseMessage<SubscriberModel>(model, ResultCode.EXCEPTION, "Exception");
        }
    }


}
