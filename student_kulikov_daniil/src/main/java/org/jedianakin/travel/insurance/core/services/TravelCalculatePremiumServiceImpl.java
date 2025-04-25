package org.jedianakin.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.jedianakin.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.jedianakin.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.jedianakin.travel.insurance.dto.ValidationError;
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
                ? getTravelCalculatePremiumResponse(request, premium.calculatePremium(request))
                : getTravelCalculatePremiumResponse(errors);
    }

    private static TravelCalculatePremiumResponse getTravelCalculatePremiumResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse getTravelCalculatePremiumResponse(TravelCalculatePremiumRequest request,
                                                                             TravelPremiumCalculationResult premiumCalculationResult) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setPersonBirthDate(request.getPersonBirthDate());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setCountry(request.getCountry());
        response.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        response.setAgreementPremium(premiumCalculationResult.totalPremium());
        response.setRisks(premiumCalculationResult.riskPremiums());
        return response;
    }
}
