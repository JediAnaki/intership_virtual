package org.jedianakin.travel.insurance.core.underwriting;

import org.jedianakin.travel.insurance.core.api.dto.RiskDTO;

import java.math.BigDecimal;
import java.util.List;

public record TravelPremiumCalculationResult(
        BigDecimal totalPremium,
        List<RiskDTO> risks
) {}
