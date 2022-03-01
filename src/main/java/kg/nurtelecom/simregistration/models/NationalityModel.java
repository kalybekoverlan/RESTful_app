package kg.nurtelecom.simregistration.models;

public class NationalityModel {

    private Long id;
    private String nationalityName;

    public NationalityModel() {
    }

    public NationalityModel(Long id, String nationalityName) {
        this.id = id;
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
