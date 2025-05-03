package org.jedianakin.travel.insurance.core.underwriting.calculators.cancellation;

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
class TravelCancellationRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final TCCountrySafetyRatingCoefficientCalculator countryCalculator;
    private final TCAgeCoefficientCalculator ageCalculator;
    private final TCTravelCostCoefficientCalculator costCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        var countryCoefficient = countryCalculator.calculate(agreement);
        var ageCoefficient = ageCalculator.calculate(person);
        var costCoefficient = costCalculator.calculate(person);
        return costCoefficient
                .add(ageCoefficient)
                .add(countryCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }

}
