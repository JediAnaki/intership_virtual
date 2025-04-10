package org.javaguru.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.RiskPremium;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    private final SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @Override
    public TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequest request) {
        List<RiskPremium> riskPremiums = calculateSelectedRisksPremium(request);
        BigDecimal totalPremium = calculateTotalPremium(riskPremiums);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private List<RiskPremium> calculateSelectedRisksPremium(TravelCalculatePremiumRequest request) {
        return selectedRisksPremiumCalculator.calculatePremiumForAllRisks(request);
    }

    private static BigDecimal calculateTotalPremium(List<RiskPremium> riskPremiums) {
        return riskPremiums.stream()
                .map(RiskPremium::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
