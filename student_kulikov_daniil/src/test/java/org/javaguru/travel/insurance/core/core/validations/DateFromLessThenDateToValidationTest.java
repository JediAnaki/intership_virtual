package org.javaguru.travel.insurance.core.core.validations;

import org.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import org.javaguru.travel.insurance.core.validations.ValidateDateFromLessThenDateTo;
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
public class DateFromLessThenDateToValidationTest {

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidateDateFromLessThenDateTo validator;

    @Test
    void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2010, 10, 10));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2010, 10, 10));

        when(errorCodeUtil.getErrorDescription("ERROR_CODE_5")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_5", errorOpt.get().getErrorCode());
        assertEquals("error description", errorOpt.get().getDescription());
    }

    @Test
    void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        //Arrange
        var request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(11, 10, 10));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(10, 10, 10));

        when(errorCodeUtil.getErrorDescription("ERROR_CODE_5")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_5", errorOpt.get().getErrorCode());
        assertEquals("error description", errorOpt.get().getDescription());
    }
}
