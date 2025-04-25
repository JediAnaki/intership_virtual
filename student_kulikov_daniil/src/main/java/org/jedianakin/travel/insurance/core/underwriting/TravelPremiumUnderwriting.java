package org.jedianakin.travel.insurance.core.underwriting;

import org.jedianakin.travel.insurance.dto.TravelCalculatePremiumRequest;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequest request);
}
