package org.jedianakin.travel.insurance.core.underwriting.calculators.cancellation;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.domain.TCAgeCoefficient;
import org.jedianakin.travel.insurance.core.repositories.TCAgeCoefficientRepository;
import org.jedianakin.travel.insurance.core.util.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TCAgeCoefficientCalculator {

    private final TCAgeCoefficientRepository ageCoefficientRepository;
    private final DateTimeUtil dateTimeUtil;

    BigDecimal calculate(PersonDTO personDTO) {
        int age = calculateAge(personDTO);
        return ageCoefficientRepository.findCoefficient(age)
                .map(TCAgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age = " + age));
    }

    private Integer calculateAge(PersonDTO personDTO) {
        LocalDate personBirthDate = personDTO.getPersonBirthDate();
        LocalDate currentDate = dateTimeUtil.getCurrentDateTime();
        return Period.between(personBirthDate, currentDate).getYears();
    }


}
