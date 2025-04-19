package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

    @Mock private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;

    @Test
    void shouldCalculateTotalPremiumAsSumOfRiskPremiums() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        List<RiskDTO> riskPremiums = List.of(
                new RiskDTO("TRAVEL_MEDICAL", BigDecimal.ONE),
                new RiskDTO("TRAVEL_EVACUATION", BigDecimal.ONE)
        );
        when(selectedRisksPremiumCalculator.calculatePremiumForAllRisks(agreement, person)).thenReturn(riskPremiums);
        TravelPremiumCalculationResult premiumCalculationResult = premiumUnderwriting.calculatePremium(agreement, person);
        assertEquals(new BigDecimal(2), premiumCalculationResult.totalPremium());
    }

}