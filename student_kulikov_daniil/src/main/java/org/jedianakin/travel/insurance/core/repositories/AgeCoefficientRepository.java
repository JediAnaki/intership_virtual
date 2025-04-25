package org.jedianakin.travel.insurance.core.repositories;

import org.jedianakin.travel.insurance.core.domain.AgeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AgeCoefficientRepository
        extends JpaRepository<AgeCoefficient, Long> {

    @Query("SELECT ac from AgeCoefficient ac " +
            "where ac.age_from <= :age " +
            "and ac.age_to >= :age")
    Optional<AgeCoefficient> findCoefficient(@Param("age") Integer age);
}
