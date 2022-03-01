package kg.nurtelecom.simregistration.entities;

import org.hibernate.annotations.OnDelete;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nationality")
public class NationalityEntity {

    @Id
    @SequenceGenerator(
            name = "nation_sequence",
            sequenceName = "nation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "nation_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "nationality_name", nullable = false, unique = true )
    private String nationalityName;

    @OneToMany(mappedBy = "nationality", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private List<SubscriberEntity> subscribers = new ArrayList<>();

    public NationalityEntity() {
    }

    public NationalityEntity(Long id) {
        this.id = id;
    }

    public NationalityEntity(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }
}
