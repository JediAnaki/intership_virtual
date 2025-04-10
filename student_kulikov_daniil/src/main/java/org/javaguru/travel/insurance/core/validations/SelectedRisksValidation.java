package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRisksValidation implements TravelRequestBatchValidation {

    private final ClassifierValueRepository classifierValueRepository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null
                ? validateSelectedRisks(request)
                : List.of();
    }

    private List<ValidationError> validateSelectedRisks(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(this::validateRiskIc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationError> validateRiskIc(String riskIc) {
        return !existInDatabase(riskIc)
                ? Optional.of(buildValidationError(riskIc))
                : Optional.empty();
    }

    private ValidationError buildValidationError(String riskIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc);
        return errorFactory.buildError("ERROR_CODE_9", List.of(placeholder));
    }

    private boolean existInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }
}
