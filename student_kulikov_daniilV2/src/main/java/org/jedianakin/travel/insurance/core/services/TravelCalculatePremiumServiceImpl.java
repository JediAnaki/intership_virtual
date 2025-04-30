package org.jedianakin.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.jedianakin.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.jedianakin.travel.insurance.core.domain.entities.AgreementEntity;
import org.jedianakin.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Transactional
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelAgreementValidator agreementValidator;
    private final AgreementPersonsPremiumCalculator agreementPersonsPremiumCalculator;
    private final AgreementTotalPremiumCalculator agreementTotalPremiumCalculator;
    private final AgreementEntityFactory agreementEntityFactory;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        if (errors.isEmpty()) {
            calculatePremium(command.getAgreement());
            AgreementEntity agreement = agreementEntityFactory.createAgreementEntity(command.getAgreement());
            command.getAgreement().setUuid(agreement.getUuid());
            return buildResponse(command.getAgreement());
        } else {
            return buildResponse(errors);
        }
    }

    private void calculatePremium(AgreementDTO agreement) {
        agreementPersonsPremiumCalculator.calculateRiskPremiums(agreement);
        agreement.setAgreementPremium(agreementTotalPremiumCalculator.calculate(agreement));
    }

    private TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(AgreementDTO agreement) {
        return new TravelCalculatePremiumCoreResult(null, agreement);
    }

}
