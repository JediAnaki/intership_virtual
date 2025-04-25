package org.jedianakin.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.jedianakin.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementPersonsPremiumCalculator {

    private final TravelPremiumUnderwriting premiumUnderwriting;

    void calculateRiskPremiums(AgreementDTO agreement) {
        agreement.getPersons().forEach(person -> {
            TravelPremiumCalculationResult calculationResult = premiumUnderwriting.calculatePremium(agreement, person);
            person.setRisks(calculationResult.risks());
        });
    }

}
