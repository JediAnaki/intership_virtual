package org.javaguru.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelCalculatePremiumUnderwritingImpl implements TravelPremiumUnderwritingImpl {

    private final List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(riskIc -> calculatePremiumForRisk(riskIc, request))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequest request) {
        var travelRiskPremiumCalculators = findRiskPremiumCalculator(riskIc);
        return travelRiskPremiumCalculators.calculatePremium(request);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return travelRiskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }
}
