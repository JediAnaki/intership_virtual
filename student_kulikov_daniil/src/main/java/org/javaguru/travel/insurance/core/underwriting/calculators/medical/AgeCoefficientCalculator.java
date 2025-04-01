package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Period;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgeCoefficientCalculator {

    private final DateTimeUtil dateTimeUtil;
    private final AgeCoefficientRepository ageCoefficientRepository;

    BigDecimal calculate(TravelCalculatePremiumRequest request) {
        int age = calculateAge(request);
        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age = " + age));
    }

    private Integer calculateAge(TravelCalculatePremiumRequest request) {
        return Period.between(request.getPersonBirthDate(), dateTimeUtil.getCurrentDateTime())
                .getYears();
    }
}
