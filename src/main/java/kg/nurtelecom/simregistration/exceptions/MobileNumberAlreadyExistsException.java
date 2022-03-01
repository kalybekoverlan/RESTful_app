package kg.nurtelecom.simregistration.exceptions;

public class MobileNumberAlreadyExistsException extends RuntimeException{

    public MobileNumberAlreadyExistsException(String message) {
        super(message);
    }
}
