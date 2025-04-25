package org.jedianakin.travel.insurance.core.validations;

import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.jedianakin.travel.insurance.dto.ValidationError;

import java.util.Optional;

public interface TravelRequestValidation {
    Optional<ValidationError> validate(TravelCalculatePremiumRequest request);
}
