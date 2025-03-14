package org.javaguru.travel.insurance.core.core.validations;

import org.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import org.javaguru.travel.insurance.core.validations.ValidateAgreementDateFrom;
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
public class AgreementDateFromValidationTest {

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidateAgreementDateFrom validator;

    @Test
    void shouldNonNullAgreementDateFrom() {
        //Arrange
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_2")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validator.validate(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_2", errorOpt.get().getErrorCode());
        assertEquals("error description", errorOpt.get().getDescription());
    }
}
