package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

class SingleErrorValidationAdapter implements TravelRequestValidation {
    private final TravelRequestBatchValidation batchValidation;

    public SingleErrorValidationAdapter(TravelRequestBatchValidation batchValidation) {
        this.batchValidation = batchValidation;
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return batchValidation.validateList(request).stream().findFirst();
    }
}
