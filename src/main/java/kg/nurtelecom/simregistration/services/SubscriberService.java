package kg.nurtelecom.simregistration.services;

import kg.nurtelecom.simregistration.exceptions.SubscriberAlreadyExistsException;
import kg.nurtelecom.simregistration.exceptions.SubscriberNotFoundException;
import kg.nurtelecom.simregistration.models.SubscriberModel;

import java.util.List;

public interface SubscriberService {

    SubscriberModel getSubscriberById(Long id) throws SubscriberNotFoundException;

    List<SubscriberModel> getAllSubscribers();

    SubscriberModel saveSubscriber(SubscriberModel model) throws SubscriberAlreadyExistsException;

    SubscriberModel deleteSubscriberById(Long id) throws SubscriberNotFoundException;

    SubscriberModel updateSubscriberById(Long id, SubscriberModel model) throws SubscriberNotFoundException;

}
