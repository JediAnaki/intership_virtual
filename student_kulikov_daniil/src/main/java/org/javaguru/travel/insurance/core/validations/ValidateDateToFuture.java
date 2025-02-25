package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class ValidateDateToFuture implements TravelRequestValidation {
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        LocalDate dateTo = request.getAgreementDateTo();
        return (dateTo != null && dateTo.isBefore(LocalDate.now()))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be in the future!"))
                : Optional.empty();
    }
}
