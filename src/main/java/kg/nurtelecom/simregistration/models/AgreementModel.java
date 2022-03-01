package kg.nurtelecom.simregistration.models;

import kg.nurtelecom.simregistration.enums.AgreementStatus;

import java.time.OffsetDateTime;

public class AgreementModel {

    private Long id;
    private OffsetDateTime createdOn;
    private SubscriberModel subscriber;
    private MobileNumberModel mobileNumberModel;
    private AgreementStatus status;

    public AgreementModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public SubscriberModel getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(SubscriberModel subscriber) {
        this.subscriber = subscriber;
    }

    public MobileNumberModel getMobileNumberModel() {
        return mobileNumberModel;
    }

    public void setMobileNumberModel(MobileNumberModel mobileNumberModel) {
        this.mobileNumberModel = mobileNumberModel;
    }

    public AgreementStatus getStatus() {
        return status;
    }

    public void setStatus(AgreementStatus status) {
        this.status = status;
    }
}
