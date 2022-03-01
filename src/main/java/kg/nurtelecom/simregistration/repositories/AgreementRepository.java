package kg.nurtelecom.simregistration.repositories;

import kg.nurtelecom.simregistration.entities.AgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends JpaRepository<AgreementEntity, Long>, JpaSpecificationExecutor<AgreementEntity> {

    @Query("SELECT s from AgreementEntity s WHERE s.mobileNumber.id = ?1 and s.subscriber.id= ?2")
    AgreementEntity findByPhoneAndSubscriber(Long mobileNumberId,   Long subscriberId);
}
