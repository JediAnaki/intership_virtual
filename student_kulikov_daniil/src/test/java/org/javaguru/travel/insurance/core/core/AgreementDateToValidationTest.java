package org.javaguru.travel.insurance.core.core;

import org.javaguru.travel.insurance.core.validations.ValidateAgreementDateTo;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AgreementDateToValidationTest {

    private ValidateAgreementDateTo validator = new ValidateAgreementDateTo();

    @Test
    void shouldNonNullAgreementDateTo() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(null);

        //Act
        Optional<ValidationError> errors = validator.execute(request);

        //Assert
        assertFalse(errors.isEmpty());
        //assertEquals(1, errors.size());
        assertEquals("agreementDateTo", errors.get().getField());
        assertEquals("Must not be empty!", errors.get().getMessage());
    }
}
