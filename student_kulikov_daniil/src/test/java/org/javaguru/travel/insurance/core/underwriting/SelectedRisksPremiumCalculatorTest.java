package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.RiskPremium;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SelectedRisksPremiumCalculatorTest {
    private SelectedRisksPremiumCalculator calculator;

    private TravelRiskPremiumCalculator riskPremiumCalculator1;
    private TravelRiskPremiumCalculator riskPremiumCalculator2;

    @BeforeEach
    public void init() {
        riskPremiumCalculator1 = mock(TravelRiskPremiumCalculator.class);
        riskPremiumCalculator2 = mock(TravelRiskPremiumCalculator.class);
        var riskPremiumCalculators = List.of(riskPremiumCalculator1, riskPremiumCalculator2);
        calculator = new SelectedRisksPremiumCalculator(riskPremiumCalculators);
    }

    @Test
    void shouldCalculatePremiumForOneRisk() {
        when(riskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculator1.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        List<RiskPremium> riskPremiums = calculator.calculatePremiumForAllRisks(request);
        assertEquals(1, riskPremiums.size());
        assertEquals("TRAVEL_MEDICAL", riskPremiums.getFirst().getRiskIc());
        assertEquals(BigDecimal.ONE, riskPremiums.getFirst().getPremium());
    }

    @Test
    void shouldCalculatePremiumForTwoRisks() {
        when(riskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculator2.getRiskIc()).thenReturn("TRAVEL_EVACUATION");

        when(riskPremiumCalculator1.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(riskPremiumCalculator2.calculatePremium(any())).thenReturn(BigDecimal.ONE);

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_EVACUATION"));
        List<RiskPremium> riskPremiums = calculator.calculatePremiumForAllRisks(request);
        assertEquals(2, riskPremiums.size());
        assertEquals("TRAVEL_MEDICAL", riskPremiums.get(0).getRiskIc());
        assertEquals(BigDecimal.ONE, riskPremiums.get(0).getPremium());
        assertEquals("TRAVEL_EVACUATION", riskPremiums.get(1).getRiskIc());
        assertEquals(BigDecimal.ONE, riskPremiums.get(1).getPremium());
    }

    @Test
    void shouldThrowExceptionWhenSelectedRiskTypeNotSupported() {
        when(riskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculator2.getRiskIc()).thenReturn("TRAVEL_EVACUATION");

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("NOT_SUPPORTED_RISK_TYPE"));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> calculator.calculatePremiumForAllRisks(request));
        assertEquals("Not supported riskIc = NOT_SUPPORTED_RISK_TYPE", exception.getMessage());
    }
}
