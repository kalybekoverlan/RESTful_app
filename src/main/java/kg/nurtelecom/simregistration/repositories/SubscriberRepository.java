package kg.nurtelecom.simregistration.repositories;

import kg.nurtelecom.simregistration.entities.SubscriberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {

    @Query("SELECT s from SubscriberEntity s WHERE s.documentNumber = ?1")
    SubscriberEntity findByDocumentNumber(String documentNumber);

    @Query("SELECT s from SubscriberEntity s WHERE s.personalNumber = ?1")
    SubscriberEntity findByPersonalNumber(String personalNumber);
}
