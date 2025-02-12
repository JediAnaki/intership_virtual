package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {

    private final TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    @Test
    @DisplayName("Null в имени")
    void shouldValidateNonNullFirstName() {
        // Arrange
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("lastName");

        // Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    @DisplayName("Null в фамилии")
    void shouldValidateNonNullLastName() {
        //Arrange
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn(null);

        //Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());

    }

    @Test
    @DisplayName("Пустая строка имя")
    void shouldValidateNoEmptyFirstName() {
        //Arrange
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("lastName");

        //Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    @DisplayName("Пустая строка фамилия")
    void shouldValidateNoEmptyLastName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("");

        List<ValidationError> errors = validator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }


}
