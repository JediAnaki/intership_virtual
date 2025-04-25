package org.jedianakin.travel.insurance.core.underwriting;

import org.jedianakin.travel.insurance.dto.RiskPremium;

import java.math.BigDecimal;
import java.util.List;

public record TravelPremiumCalculationResult(
        BigDecimal totalPremium,
        List<RiskPremium> riskPremiums
) {}

