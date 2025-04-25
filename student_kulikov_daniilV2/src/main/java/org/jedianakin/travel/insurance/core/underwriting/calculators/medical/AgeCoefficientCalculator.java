package org.jedianakin.travel.insurance.core.underwriting.calculators.medical;

import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.domain.AgeCoefficient;
import org.jedianakin.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.jedianakin.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Component
class AgeCoefficientCalculator {

    private final Boolean medicalRiskAgeCoefficientEnabled;

    private final DateTimeUtil dateTimeUtil;
    private final AgeCoefficientRepository ageCoefficientRepository;

    AgeCoefficientCalculator(@Value( "${medical.risk.age.coefficient.enabled:false}" )
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
        LocalDate personBirthDate = person.getPersonBirthDate();
        LocalDate currentDate = dateTimeUtil.getCurrentDateTime();
        return Period.between(personBirthDate, currentDate).getYears();
    }

    private static BigDecimal getDefaultValue() {
        return BigDecimal.ONE;
    }

}
