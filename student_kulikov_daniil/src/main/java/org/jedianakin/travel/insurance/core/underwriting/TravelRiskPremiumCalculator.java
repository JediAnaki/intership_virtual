package org.jedianakin.travel.insurance.core.underwriting;

import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);

    String getRiskIc();
}
