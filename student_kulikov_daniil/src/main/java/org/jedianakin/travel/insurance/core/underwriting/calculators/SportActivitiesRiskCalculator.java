package org.jedianakin.travel.insurance.core.underwriting.calculators;

import org.jedianakin.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SportActivitiesRiskCalculator implements TravelRiskPremiumCalculator {
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_SPORT_ACTIVITIES";
    }
}
