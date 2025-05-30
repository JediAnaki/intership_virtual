package org.jedianakin.travel.insurance.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.jedianakin.travel.insurance.core.repositories.ClassifierValueRepository;
import org.jedianakin.travel.insurance.core.util.Placeholder;
import org.jedianakin.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRisksValidation extends TravelAgreementFieldValidationImpl {

    private final ClassifierValueRepository classifierValueRepository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement) {
        return agreement.getSelectedRisks() != null
                ? validateSelectedRisks(agreement)
                : List.of();
    }

    private List<ValidationErrorDTO> validateSelectedRisks(AgreementDTO agreement) {
        return agreement.getSelectedRisks().stream()
                .map(this::validateRiskIc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationErrorDTO> validateRiskIc(String riskIc) {
        return !existInDatabase(riskIc)
                ? Optional.of(buildValidationError(riskIc))
                : Optional.empty();
    }

    private ValidationErrorDTO buildValidationError(String riskIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc);
        return errorFactory.buildError("ERROR_CODE_9", List.of(placeholder));
    }

    private boolean existInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }

}
