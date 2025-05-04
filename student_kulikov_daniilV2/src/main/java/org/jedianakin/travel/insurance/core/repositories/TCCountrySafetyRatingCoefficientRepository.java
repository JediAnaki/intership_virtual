package org.jedianakin.travel.insurance.core.repositories;

import org.jedianakin.travel.insurance.core.domain.TCCountrySafetyRatingCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TCCountrySafetyRatingCoefficientRepository
        extends JpaRepository<TCCountrySafetyRatingCoefficient, Long> {

    @Cacheable(cacheNames = {"tcCountrySafetyRatingCache"}, key = "#p0", unless="#result == null")
    Optional<TCCountrySafetyRatingCoefficient> findByCountryIc(String countryIc);
}
