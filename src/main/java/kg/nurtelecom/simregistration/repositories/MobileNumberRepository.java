package kg.nurtelecom.simregistration.repositories;

import kg.nurtelecom.simregistration.entities.MobileNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumberEntity, Long> {

    @Query("SELECT s from MobileNumberEntity s WHERE s.number = ?1")
    MobileNumberEntity findByMobileNumber(String number);

}
