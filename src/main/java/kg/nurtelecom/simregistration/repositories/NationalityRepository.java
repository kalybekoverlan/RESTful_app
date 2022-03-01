package kg.nurtelecom.simregistration.repositories;

import kg.nurtelecom.simregistration.entities.NationalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends JpaRepository<NationalityEntity, Long> {

    @Query("SELECT s from NationalityEntity s WHERE s.nationalityName = ?1")
    NationalityEntity findByNationalityName(String nationality);
}
