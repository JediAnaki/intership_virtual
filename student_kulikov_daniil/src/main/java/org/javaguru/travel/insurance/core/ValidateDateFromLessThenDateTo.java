package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidateDateFromLessThenDateTo {
    public Optional<ValidationError> validateDateFromLessThenDateTo(TravelCalculatePremiumRequest request) {
        LocalDate dateFrom = request.getAgreementDateFrom();
        LocalDate dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.isEqual(dateTo) || dateFrom.isAfter(dateTo)))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less then agreementDateTo!"))
                : Optional.empty();
    }
}
