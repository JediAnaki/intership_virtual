package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;

interface TravelRequestBatchValidation {
    List<ValidationError> validateList(TravelCalculatePremiumRequest request);
}
