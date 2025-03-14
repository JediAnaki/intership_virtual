package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

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
