package org.javaguru.travel.insurance.core.core;

import org.javaguru.travel.insurance.core.validations.ValidatePersonFirstName;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonFirstNameValidationTest {

    private ValidatePersonFirstName validator = new ValidatePersonFirstName();

    @Test
    @DisplayName("Null в имени")
    void shouldValidateNonNullFirstName() {
        // Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now());

        // Act
        Optional<ValidationError> errors = validator.execute(request);

        //Assert
        assertTrue(errors.isPresent());
        assertEquals("personFirstName", errors.get().getField());
        assertEquals("Must not be empty!", errors.get().getMessage());
    }

    @Test
    @DisplayName("Пустая строка имя")
    void shouldValidateNoEmptyFirstName() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now());

        //Act
        Optional<ValidationError> errors = validator.execute(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personFirstName", errors.get().getField());
        assertEquals("Must not be empty!", errors.get().getMessage());
    }
}
