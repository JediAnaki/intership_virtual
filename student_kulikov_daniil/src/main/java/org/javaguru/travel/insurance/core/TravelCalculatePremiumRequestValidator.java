package org.javaguru.travel.insurance.core;

import lombok.NonNull;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class TravelCalculatePremiumRequestValidator {

    private final ValidatePersonFirstName validatePersonFirstNameClass;
    private final ValidatePersonLastName validatePersonLastNameClass;
    private final ValidateAgreementDateFrom validateAgreementDateFromClass;
    private final ValidateAgreementDateTo validateAgreementDateToClass;
    private final ValidateDateFromLessThenDateTo validateDateFromLessThenDateToClass;
    private final ValidateDateFromFuture validateDateFromFutureClass;
    private final ValidateDateToFuture validateDateToFutureClass;

    public TravelCalculatePremiumRequestValidator(ValidatePersonFirstName validatePersonFirstNameClass,
                                                  ValidatePersonLastName validatePersonLastNameClass,
                                                  ValidateAgreementDateFrom validateAgreementDateFromClass,
                                                  ValidateAgreementDateTo validateAgreementDateToClass,
                                                  ValidateDateFromLessThenDateTo validateDateFromLessThenDateToClass,
                                                  ValidateDateFromFuture validateDateFromFutureClass,
                                                  ValidateDateToFuture validateDateToFutureClass) {
        this.validatePersonFirstNameClass = validatePersonFirstNameClass;
        this.validatePersonLastNameClass = validatePersonLastNameClass;
        this.validateAgreementDateFromClass = validateAgreementDateFromClass;
        this.validateAgreementDateToClass = validateAgreementDateToClass;
        this.validateDateFromLessThenDateToClass = validateDateFromLessThenDateToClass;
        this.validateDateFromFutureClass = validateDateFromFutureClass;
        this.validateDateToFutureClass = validateDateToFutureClass;
    }



    public List<ValidationError> validate(@NonNull TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstNameClass.validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastNameClass.validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFromClass.validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateToClass.validateAgreementDateTo(request).ifPresent(errors::add);
        validateDateFromLessThenDateToClass.validateDateFromLessThenDateTo(request).ifPresent(errors::add);
        validateDateFromFutureClass.validateDateFromFuture(request).ifPresent(errors::add);
        validateDateToFutureClass.validateDateToFuture(request).ifPresent(errors::add);
        return errors;
    }


}
