package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
class AgreementDateToInFutureValidationTest {

    @Mock private DateTimeUtil dateTimeUtil;
    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private AgreementDateToInFutureValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateToInThePast() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getAgreementDateTo()).thenReturn(LocalDate.of(2020, 1, 1));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.of(2023, 1, 1));
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_3")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToInTheFuture() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 1, 1));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.of(2023, 1, 1));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }
}