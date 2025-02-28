package org.javaguru.travel.insurance.core.core;

import org.javaguru.travel.insurance.core.validations.ValidateDateFromFuture;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateFromInFutureValidationTest {

    @Mock
    private ValidateDateFromFuture validator;

    @Test
    void shouldReturnErrorWhenAgreementDateFromInThePast() {
        var request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.of(2020, 10, 10));
        request.setAgreementDateTo(LocalDate.of(2023, 10, 10));

        when(validator.execute(request)).thenReturn(
                Optional.of(new ValidationError("agreementDateFrom", "Must be in the future!"))
        );

        Optional<ValidationError> errors = validator.execute(request);

        assertTrue(errors.isPresent());
        assertEquals("agreementDateFrom", errors.get().getField());
        assertEquals("Must be in the future!", errors.get().getMessage());
    }
}

