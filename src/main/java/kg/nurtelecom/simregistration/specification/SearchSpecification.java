package kg.nurtelecom.simregistration.specification;

import kg.nurtelecom.simregistration.entities.AgreementEntity;
import kg.nurtelecom.simregistration.models.SearchModel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SearchSpecification implements Specification<AgreementEntity> {

    private SearchModel model;

    public SearchSpecification(SearchModel model) {
        this.model = model;
    }

    @Override
    public Predicate toPredicate(Root<AgreementEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if(model.getDocumentnumber() != null){
            predicates.add(criteriaBuilder.equal(root.join("subscriber").get("documentNumber"), model.getDocumentnumber()));
        } if(model.getPersonalnumber() != null){
            predicates.add(criteriaBuilder.equal(root.join("subscriber").get("personalNumber"), model.getPersonalnumber()));
        } if(model.getMobilenumber() != null){
            predicates.add(criteriaBuilder.equal(root.join("mobileNumber").get("number"), model.getMobilenumber()));
        } if(model.getLastname() != null){
            predicates.add(criteriaBuilder.equal(root.join("subscriber").get("surname"), model.getLastname()));
        } if(model.getName() != null){
            predicates.add(criteriaBuilder.equal(root.join("subscriber").get("name"), model.getName()));
        }

        Predicate[] predicate = new Predicate[predicates.size()];

        return criteriaBuilder.and(predicates.toArray(predicate));
    }

}
