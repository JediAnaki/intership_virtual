package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelAgreementValidator agreementValidator;
    @Mock private AgreementPersonsPremiumCalculator agreementPersonsPremiumCalculator;
    @Mock private AgreementTotalPremiumCalculator agreementTotalPremiumCalculator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;


    @Test
    public void shouldReturnValidationErrors() {
        var agreement = new AgreementDTO();
        var command = new TravelCalculatePremiumCoreCommand(agreement);

        var validationErrorDTO = new ValidationErrorDTO("Error code", "Error description");
        when(agreementValidator.validate(agreement)).thenReturn(List.of(validationErrorDTO));

        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(command);

        assertEquals(1, result.getErrors().size());
        assertEquals("Error code", result.getErrors().getFirst().getErrorCode());
        assertEquals("Error description", result.getErrors().getFirst().getDescription());
        verifyNoInteractions(agreementPersonsPremiumCalculator, agreementPersonsPremiumCalculator);
    }

    @Test
    public void shouldReturnPremiumForOnePerson() {
        var agreement = new AgreementDTO();
        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        verify(agreementPersonsPremiumCalculator).calculateRiskPremiumsForAllPersons(agreement);

    }

    @Test
    public void shouldReturnPremiumForTwoPersons() {
        var agreement = new AgreementDTO();
        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        when(agreementTotalPremiumCalculator.calculateTotalAgreementPremium(agreement)).thenReturn(BigDecimal.ONE);
        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        assertEquals(BigDecimal.ONE, result.getAgreement().getAgreementPremium());
    }

}