package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.util.DateTimeUtil;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgreementDateFromInFutureValidationTest {

    @Mock private DateTimeUtil dateTimeUtil;
    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private ValidateDateFromFuture validator;

    @Test
    public void shouldReturnErrorWhenAgreementDateFromInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2020, 1, 1));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.of(2023, 1, 1));
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_1")).thenReturn(validationError);
        Optional<ValidationError> errorOpt = validator.validate(request);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromInFuture() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 1, 1));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.of(2023, 1, 1));
        Optional<ValidationError> errorOpt = validator.validate(request);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }
}

