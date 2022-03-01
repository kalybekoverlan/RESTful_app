package kg.nurtelecom.simregistration.entities;

import kg.nurtelecom.simregistration.enums.MobileNumberStatus;
import org.hibernate.annotations.OnDelete;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mobile_number")
public class MobileNumberEntity {

    @Id
    @SequenceGenerator(
            name = "mobile_sequence",
            sequenceName = "mobile_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mobile_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private MobileNumberStatus status;

    @OneToMany(mappedBy = "mobileNumber", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private List<AgreementEntity> agreements = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "status_id")
//    private MobileNumberStatusEntity status;

    public MobileNumberEntity() {
    }

    public MobileNumberEntity(Long id) {
        this.id = id;
    }

    public MobileNumberEntity(Long id, String number, MobileNumberStatus status) {
        this.id = id;
        this.number = number;
        this.status = status;
    }

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
