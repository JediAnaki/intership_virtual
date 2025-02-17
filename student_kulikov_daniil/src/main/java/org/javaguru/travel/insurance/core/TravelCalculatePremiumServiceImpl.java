package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumUnderwriting premium;
    private final TravelCalculatePremiumRequestValidator validator;

    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = validator.validate(request);
        return !errors.isEmpty()
                ? getTravelCalculatePremiumResponse(errors)
                : getTravelCalculatePremiumResponse(request, premium.calculatePremium(request));
    }

    private static TravelCalculatePremiumResponse getTravelCalculatePremiumResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse getTravelCalculatePremiumResponse(TravelCalculatePremiumRequest request, BigDecimal premiumBetwen) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(premiumBetwen);
        return response;
    }
}
