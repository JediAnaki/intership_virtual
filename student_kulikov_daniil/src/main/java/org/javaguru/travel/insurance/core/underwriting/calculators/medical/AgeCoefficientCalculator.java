package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Period;

@Component
class AgeCoefficientCalculator {

    private final Boolean medicalRiskAgeCoefficientEnabled;

    private final DateTimeUtil dateTimeUtil;
    private final AgeCoefficientRepository ageCoefficientRepository;

    AgeCoefficientCalculator(@Value("true")
                             Boolean medicalRiskAgeCoefficientEnabled,
                             DateTimeUtil dateTimeUtil,
                             AgeCoefficientRepository ageCoefficientRepository) {
        this.medicalRiskAgeCoefficientEnabled = medicalRiskAgeCoefficientEnabled;
        this.dateTimeUtil = dateTimeUtil;
        this.ageCoefficientRepository = ageCoefficientRepository;
    }

    BigDecimal calculate(TravelCalculatePremiumRequest request) {
        return medicalRiskAgeCoefficientEnabled
                ? getCoefficient(request)
                : getDefaultValue();
    }

    private BigDecimal getCoefficient(TravelCalculatePremiumRequest request) {
        int age = calculateAge(request);
        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age = " + age));
    }

    private Integer calculateAge(TravelCalculatePremiumRequest request) {
        return Period.between(request.getPersonBirthDate(), dateTimeUtil.getCurrentDateTime())
                .getYears();
    }

    private static BigDecimal getDefaultValue() {
        return BigDecimal.ONE;
    }
}
