package org.jedianakin.travel.insurance.core.validations;

import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.jedianakin.travel.insurance.dto.ValidationError;

import java.util.List;

public interface TravelCalculatePremiumRequestValidator {
    List<ValidationError> validate(TravelCalculatePremiumRequest request);
}
