package org.jedianakin.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.util.DateTimeUtil;
import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.jedianakin.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidateDateToFuture implements TravelRequestValidation {

    private final DateTimeUtil dateTimeUtil;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        LocalDate dateTo = request.getAgreementDateTo();
        LocalDate currentDateTime = dateTimeUtil.getCurrentDateTime();
        return (dateTo != null && dateTo.isBefore(currentDateTime))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }
}
