package org.jedianakin.travel.insurance.core.validations;

import org.jedianakin.travel.insurance.core.util.DateTimeUtil;
import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.jedianakin.travel.insurance.dto.ValidationError;
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
public class AgreementDateToInFutureValidationTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private ValidateDateToFuture validator;


    @Test
    void shouldReturnErrorWhenAgreementDateToInThePast() {
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2020, 3, 10));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.of(2025, 10, 10));
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_3")).thenReturn(validationError);
        Optional<ValidationError> errorOpt = validator.validate(request);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToInTheFuture() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 1, 1));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.of(2023, 1, 1));
        Optional<ValidationError> errorOpt = validator.validate(request);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }
}
