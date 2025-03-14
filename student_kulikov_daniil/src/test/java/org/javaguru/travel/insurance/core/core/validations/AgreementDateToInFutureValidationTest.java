package org.javaguru.travel.insurance.core.core.validations;

import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import org.javaguru.travel.insurance.core.validations.ValidateDateToFuture;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateToInFutureValidationTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidateDateToFuture validator;

    @Test
    void shouldReturnErrorWhenAgreementDateToInThePast() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.of(2025, 3, 10));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2020, 10, 10));

        when(errorCodeUtil.getErrorDescription("ERROR_CODE_3")).thenReturn("error description");
        Optional<ValidationError> errors = validator.validate(request);

        assertTrue(errors.isPresent());
        assertEquals("ERROR_CODE_3", errors.get().getErrorCode());
        assertEquals("error description", errors.get().getDescription());
    }
}
