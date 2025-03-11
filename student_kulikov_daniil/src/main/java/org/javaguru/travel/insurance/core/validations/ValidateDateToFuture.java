package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidateDateToFuture implements TravelRequestValidation {

    private final ErrorCodeUtil errorCodeUtil;
    private final DateTimeUtil dateTimeUtil;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        LocalDate dateTo = request.getAgreementDateTo();
        LocalDate currentDateTime = dateTimeUtil.getCurrentDateTime();
        return (dateTo != null && dateTo.isBefore(currentDateTime))
                ? Optional.of(buildError("ERROR_CODE_3"))
                : Optional.empty();
    }

    private ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }


}
