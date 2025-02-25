package org.javaguru.travel.insurance.core.core;

import org.javaguru.travel.insurance.core.validations.ValidateDateFromLessThenDateTo;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateFromLessThenDateToValidationTest {


    private ValidateDateFromLessThenDateTo validator = new ValidateDateFromLessThenDateTo();

    @Test
    void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2010, 10, 10));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2010, 10, 10));

        //Act
        Optional<ValidationError> errors = validator.execute(request);

        //Assert
        assertFalse(errors.isEmpty());
        //assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get().getField());
        assertEquals("Must be less then agreementDateTo!", errors.get().getMessage());
    }

    @Test
    void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(11, 10, 10));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(10, 10, 10));

        //Act
        Optional<ValidationError> errors = validator.execute(request);

        //Assert
        assertFalse(errors.isEmpty());
        //assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get().getField());
        assertEquals("Must be less then agreementDateTo!", errors.get().getMessage());
    }
}
