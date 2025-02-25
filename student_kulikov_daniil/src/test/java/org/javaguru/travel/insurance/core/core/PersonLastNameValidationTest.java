package org.javaguru.travel.insurance.core.core;

import org.javaguru.travel.insurance.core.validations.ValidatePersonLastName;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonLastNameValidationTest {

    private ValidatePersonLastName validator = new ValidatePersonLastName();

    @Test
    @DisplayName("Null в фамилии")
    void shouldValidateNonNullLastName() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now());

        //Act
        Optional<ValidationError> errors = validator.execute(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personLastName", errors.get().getField());
        assertEquals("Must not be empty!", errors.get().getMessage());

    }

    @Test
    @DisplayName("Пустая строка фамилия")
    void shouldValidateNoEmptyLastName() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now());

        //Act
        Optional<ValidationError> errors = validator.execute(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personLastName", errors.get().getField());
        assertEquals("Must not be empty!", errors.get().getMessage());
    }
}
