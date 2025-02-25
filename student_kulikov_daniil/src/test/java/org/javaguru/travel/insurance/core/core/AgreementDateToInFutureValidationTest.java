package org.javaguru.travel.insurance.core.core;

import org.javaguru.travel.insurance.core.validations.ValidateDateToFuture;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AgreementDateToInFutureValidationTest {

    private ValidateDateToFuture validator = new ValidateDateToFuture();

    @Test
    void shouldReturnErrorWhenAgreementDateToInThePast() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2026, 10, 10));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2020, 10, 10));

        //Act
        Optional<ValidationError> errors = validator.execute(request);

        //Assert
        assertFalse(errors.isEmpty());
        //assertEquals(2, errors.size());
        assertEquals("agreementDateTo", errors.get().getField());
        assertEquals("Must be in the future!", errors.get().getMessage());
    }
}
