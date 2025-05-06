package org.jedianakin.travel.insurance.core.underwriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jedianakin.travel.insurance.core.api.dto.RiskDTO;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class TravelPremiumCalculationResult {

    private BigDecimal totalPremium;

    private List<RiskDTO> risks;

}
