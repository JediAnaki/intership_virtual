package org.jedianakin.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final TMDayCountCalculator TMDayCountCalculator;
    private final TMCountryDefaultDayRateCalculator TMCountryDefaultDayRateCalculator;
    private final TMAgeCoefficientCalculator TMAgeCoefficientCalculator;
    private final TMRiskLimitLevelCalculator TMRiskLimitLevelCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        var daysCount = TMDayCountCalculator.calculate(agreement);
        var countryDefaultRate = TMCountryDefaultDayRateCalculator.calculate(agreement);
        var ageCoefficient = TMAgeCoefficientCalculator.calculate(person);
        var riskLimitLevel = TMRiskLimitLevelCalculator.calculate(person);
        return countryDefaultRate
                .multiply(daysCount)
                .multiply(ageCoefficient)
                .multiply(riskLimitLevel)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}
