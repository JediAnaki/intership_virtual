package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());

        long betweenDays = calculateAgreeBetweenPriceDays(request);
        response.setAgreementPrice(BigDecimal.valueOf(betweenDays));

        return response;
    }

    private long calculateAgreeBetweenPriceDays(TravelCalculatePremiumRequest request) {
        return ChronoUnit.DAYS.between(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }

}
