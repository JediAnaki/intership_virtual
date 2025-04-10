package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

public interface TravelRequestValidation {
    Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request);
}
