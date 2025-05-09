package org.jedianakin.travel.insurance.core.underwriting.calculators.medical;

import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.domain.TMMedicalRiskLimitLevel;
import org.jedianakin.travel.insurance.core.repositories.TMMedicalRiskLimitLevelRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TMRiskLimitLevelCalculator {

    private final Boolean medicalRiskLimitLevelEnabled;

    private final TMMedicalRiskLimitLevelRepository riskLimitLevelRepository;

    TMRiskLimitLevelCalculator(@Value( "${medical.risk.limit.level.enabled:false}" )
                               Boolean medicalRiskLimitLevelEnabled,
                               TMMedicalRiskLimitLevelRepository riskLimitLevelRepository) {
        this.medicalRiskLimitLevelEnabled = medicalRiskLimitLevelEnabled;
        this.riskLimitLevelRepository = riskLimitLevelRepository;
    }

    BigDecimal calculate(PersonDTO person) {
        return medicalRiskLimitLevelEnabled
                ? getCoefficient(person)
                : getDefaultValue();
    }

    private BigDecimal getCoefficient(PersonDTO person) {
        return riskLimitLevelRepository.findByMedicalRiskLimitLevelIc(person.getMedicalRiskLimitLevel())
                .map(TMMedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Medical risk limit level not found by = " + person.getMedicalRiskLimitLevel()));
    }

    private static BigDecimal getDefaultValue() {
        return BigDecimal.ONE;
    }

}
