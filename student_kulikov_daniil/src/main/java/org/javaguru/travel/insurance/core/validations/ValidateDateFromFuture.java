package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidateDateFromFuture implements TravelRequestValidation{
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        LocalDate dateFrom = request.getAgreementDateFrom();
        LocalDate dateTo = LocalDate.now();
        return (dateFrom != null && dateFrom.isBefore(dateTo))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be in the future!"))
                : Optional.empty();
    }
}
