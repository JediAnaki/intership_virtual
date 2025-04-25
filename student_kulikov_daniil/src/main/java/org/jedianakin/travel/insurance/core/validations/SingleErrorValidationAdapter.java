package org.jedianakin.travel.insurance.core.validations;

import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.jedianakin.travel.insurance.dto.ValidationError;

import java.util.Optional;

class SingleErrorValidationAdapter implements TravelRequestValidation {
    private final TravelRequestBatchValidation batchValidation;

    public SingleErrorValidationAdapter(TravelRequestBatchValidation batchValidation) {
        this.batchValidation = batchValidation;
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return batchValidation.validateList(request).stream().findFirst();
    }
}
