package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class MedicalRiskCalculator implements TravelRiskPremiumCalculator {

    private final DayCountCalculator dayCountCalculator;
    private final AgeCoefficientCalculator ageCoefficientCalculator;
    private final CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    private final RiskLimitLevelCalculator riskLimitLevelCalculator;


    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysCount = dayCountCalculator.calculate(request);
        var countryDefaultRate = countryDefaultDayRateCalculator.calculate(request);
        var ageCoefficient = ageCoefficientCalculator.calculate(request);
        var riskLimitLevel = riskLimitLevelCalculator.calculate(request);
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
