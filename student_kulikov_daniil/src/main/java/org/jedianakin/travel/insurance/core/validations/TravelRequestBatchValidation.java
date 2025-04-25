package org.jedianakin.travel.insurance.core.validations;

import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.jedianakin.travel.insurance.dto.ValidationError;

import java.util.List;

interface TravelRequestBatchValidation {
    List<ValidationError> validateList(TravelCalculatePremiumRequest request);
}
