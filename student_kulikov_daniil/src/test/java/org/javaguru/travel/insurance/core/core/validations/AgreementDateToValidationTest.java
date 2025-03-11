package org.javaguru.travel.insurance.core.core.validations;

import org.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import org.javaguru.travel.insurance.core.validations.ValidateAgreementDateTo;
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
public class AgreementDateToValidationTest {

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidateAgreementDateTo validator;

    @Test
    void shouldNonNullAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_4")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validator.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_4", errorOpt.get().getErrorCode());
        assertEquals("error description", errorOpt.get().getDescription());
    }
}
