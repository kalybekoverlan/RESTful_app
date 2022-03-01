package kg.nurtelecom.simregistration.models;

import kg.nurtelecom.simregistration.enums.MobileNumberStatus;

public class MobileNumberModel {

    private Long id;
    private String number;
    private MobileNumberStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public MobileNumberStatus getStatus() {
        return status;
    }

    public void setStatus(MobileNumberStatus status) {
        this.status = status;
    }
}
