package org.jedianakin.travel.insurance.core.validations.agreement;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.jedianakin.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgreementDateToValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private AgreementDateToValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateToIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getAgreementDateTo()).thenReturn(null);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_4")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToIsPresent() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 1, 1));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }
}