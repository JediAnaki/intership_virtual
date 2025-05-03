package org.jedianakin.travel.insurance.core.underwriting.calculators.cancellation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.domain.TCTravelCostCoefficient;
import org.jedianakin.travel.insurance.core.repositories.TCTravelCostCoefficientRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TCTravelCostCoefficientCalculator {

    private final TCTravelCostCoefficientRepository tcTravelCostCoefficientRepository;

    BigDecimal calculate(PersonDTO person) {
        return tcTravelCostCoefficientRepository.findCoefficient(person.getTravelCost())
                .map(TCTravelCostCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Travel Cost coefficient not found for travel cost = " + person.getTravelCost()));
    }

}
