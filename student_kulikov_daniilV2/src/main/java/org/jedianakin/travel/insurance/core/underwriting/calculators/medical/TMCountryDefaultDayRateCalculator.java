package org.jedianakin.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.domain.TMCountryDefaultDayRate;
import org.jedianakin.travel.insurance.core.repositories.TMCountryDefaultDayRateRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TMCountryDefaultDayRateCalculator {

    private final TMCountryDefaultDayRateRepository TMCountryDefaultDayRateRepository;

    BigDecimal calculate(AgreementDTO agreement) {
        return TMCountryDefaultDayRateRepository.findByCountryIc(agreement.getCountry())
                .map(TMCountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + agreement.getCountry()));
    }

}
