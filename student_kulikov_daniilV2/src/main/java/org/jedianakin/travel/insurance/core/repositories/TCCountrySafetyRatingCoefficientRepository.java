package org.jedianakin.travel.insurance.core.repositories;

import org.jedianakin.travel.insurance.core.domain.TCCountrySafetyRatingCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TCCountrySafetyRatingCoefficientRepository
        extends JpaRepository<TCCountrySafetyRatingCoefficient, Long> {

    Optional<TCCountrySafetyRatingCoefficient> findByCountryIc(String countryIc);
}
