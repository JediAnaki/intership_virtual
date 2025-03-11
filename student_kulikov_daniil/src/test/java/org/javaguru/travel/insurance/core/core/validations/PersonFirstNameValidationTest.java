package org.javaguru.travel.insurance.core.core.validations;

import org.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import org.javaguru.travel.insurance.core.validations.ValidatePersonFirstName;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonFirstNameValidationTest {

    @Mock private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidatePersonFirstName validator;

    @Test
    void shouldValidateNonNullFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_7")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_7", errorOpt.get().getErrorCode());
        assertEquals("error description", errorOpt.get().getDescription());
    }

    @Test
    void shouldValidateNoEmptyFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_7")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_7", errorOpt.get().getErrorCode());
        assertEquals("error description", errorOpt.get().getDescription());
    }
}
