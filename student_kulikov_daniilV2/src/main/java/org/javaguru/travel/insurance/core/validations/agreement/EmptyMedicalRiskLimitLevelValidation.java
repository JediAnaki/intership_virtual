package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmptyMedicalRiskLimitLevelValidation extends TravelAgreementFieldValidationImpl {

    private final Boolean medicalRiskLimitLevelEnabled;

    private final ValidationErrorFactory errorFactory;

    EmptyMedicalRiskLimitLevelValidation(@Value("${medical.risk.limit.level.enabled:false}")
                                                Boolean medicalRiskLimitLevelEnabled,
                                                ValidationErrorFactory errorFactory) {
        this.medicalRiskLimitLevelEnabled = medicalRiskLimitLevelEnabled;
        this.errorFactory = errorFactory;
    }

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return (isMedicalRiskLimitLevelEnabled()
                && containsTravelMedical(agreement)
                && isMedicalRiskLimitLevelIsNullOrBlank(agreement))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }

    private boolean isMedicalRiskLimitLevelEnabled() {
        return medicalRiskLimitLevelEnabled;
    }

    private boolean containsTravelMedical(AgreementDTO agreement) {
        return agreement.getSelectedRisks() != null
                && agreement.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private boolean isMedicalRiskLimitLevelIsNullOrBlank(AgreementDTO agreement) {
        return agreement.getMedicalRiskLimitLevel() == null || agreement.getMedicalRiskLimitLevel().isBlank();
    }

}
