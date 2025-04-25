package org.jedianakin.travel.insurance.core.underwriting;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person);

}
