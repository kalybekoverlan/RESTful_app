package kg.nurtelecom.simregistration.entities;

import kg.nurtelecom.simregistration.enums.AgreementStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "agreement")
public class AgreementEntity {

    @Id
    @SequenceGenerator(
            name = "agreement_sequence",
            sequenceName = "agreement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agreement_sequence"
    )
    @Column(name = "id", updatable = false, columnDefinition = "serial")
    private Long id;

    @Column(name = "created_on", columnDefinition="TIMESTAMP WITH TIME ZONE")
    @CreationTimestamp
    private OffsetDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private SubscriberEntity subscriber;

    @ManyToOne
    @JoinColumn(name = "mobile_number_id")
    private MobileNumberEntity mobileNumber;

//    @ManyToOne
//    @JoinColumn(name = "agreement_status_id")
//    private AgreementStatus agreementStatus;

    @Column(name = "status")
    private AgreementStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubscriberEntity getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(SubscriberEntity subscriber) {
        this.subscriber = subscriber;
    }

    public AgreementStatus getStatus() {
        return status;
    }

    public void setStatus(AgreementStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public MobileNumberEntity getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(MobileNumberEntity mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
