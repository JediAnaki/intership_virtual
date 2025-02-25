package org.javaguru.travel.insurance.core.core;

import org.javaguru.travel.insurance.core.validations.ValidateAgreementDateFrom;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Component
public class AgreementDateFromValidationTest {

    private ValidateAgreementDateFrom validator = new ValidateAgreementDateFrom();

    @Test
    void shouldNonNullAgreementDateFrom() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now());

        //Act
        Optional<ValidationError> errors = validator.execute(request);

        //Assert
        assertFalse(errors.isEmpty());
        //assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get().getField());
        assertEquals("Must not be empty!", errors.get().getMessage());
    }
}
