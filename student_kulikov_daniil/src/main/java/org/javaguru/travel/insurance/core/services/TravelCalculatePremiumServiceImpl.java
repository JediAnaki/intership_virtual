package org.javaguru.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelPremiumUnderwriting premium;
    private final TravelCalculatePremiumRequestValidator validator;

    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = validator.validate(request);
        return errors.isEmpty()
                ? getTravelCalculatePremiumResponse(errors)
                : getTravelCalculatePremiumResponse(request, premium.calculatePremium(request));
    }

    private static TravelCalculatePremiumResponse getTravelCalculatePremiumResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse getTravelCalculatePremiumResponse(TravelCalculatePremiumRequest request,
                                                                             TravelPremiumCalculationResult premiumCalculationResult) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPremium(premiumCalculationResult.totalPremium());
        response.setRisks(premiumCalculationResult.riskPremiums());
        return response;
    }
}
