package org.jedianakin.travel.insurance.core.underwriting.calculators;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelEvacuationRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_EVACUATION";
    }

}
