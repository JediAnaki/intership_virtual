package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
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

    BigDecimal calculate(PersonDTO person) {
        return medicalRiskAgeCoefficientEnabled
                ? getCoefficient(person)
                : getDefaultValue();
    }

    private BigDecimal getCoefficient(PersonDTO person) {
        int age = calculateAge(person);
        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age = " + age));
    }

    private Integer calculateAge(PersonDTO person) {
        return Period.between(person.getPersonBirthDate(), dateTimeUtil.getCurrentDateTime())
                .getYears();
    }

    private static BigDecimal getDefaultValue() {
        return BigDecimal.ONE;
    }
}
