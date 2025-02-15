package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {

    private final TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    @Test
    @DisplayName("Null в имени")
    void shouldValidateNonNullFirstName() {
        // Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDateTime.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDateTime.now());

        // Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personFirstName", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());
    }

    @Test
    @DisplayName("Null в фамилии")
    void shouldValidateNonNullLastName() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(LocalDateTime.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDateTime.now());

        //Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personLastName", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());

    }

    @Test
    @DisplayName("Пустая строка имя")
    void shouldValidateNoEmptyFirstName() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDateTime.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDateTime.now());

        //Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personFirstName", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());
    }

    @Test
    @DisplayName("Пустая строка фамилия")
    void shouldValidateNoEmptyLastName() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(LocalDateTime.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDateTime.now());

        //Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals("personLastName", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());
    }

    @Test
    void shouldNonNullAgreementDateFrom() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(LocalDateTime.now());

        //Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());
    }

    @Test
    void shouldNonNullAgreementDateTo() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDateTime.now());
        when(request.getAgreementDateTo()).thenReturn(null);

        //Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateTo", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());
    }

    @Test
    void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDateTime.of(10, 10, 10, 0, 0, 0));
        when(request.getAgreementDateTo()).thenReturn(LocalDateTime.of(10, 10, 10, 0, 0, 0));

        //Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.getFirst().getField());
        assertEquals("Must be less then agreementDateTo!", errors.getFirst().getMessage());
    }

    @Test
    void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDateTime.of(11, 10, 10, 0, 0, 0));
        when(request.getAgreementDateTo()).thenReturn(LocalDateTime.of(10, 10, 10, 0, 0, 0));

        //Act
        List<ValidationError> errors = validator.validate(request);

        //Assert
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.getFirst().getField());
        assertEquals("Must be less then agreementDateTo!", errors.getFirst().getMessage());
    }
}
