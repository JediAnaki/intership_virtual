package org.jedianakin.travel.insurance.core.services;

import org.jedianakin.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.jedianakin.travel.insurance.core.api.command.TravelGetAgreementCoreResult;

public interface TravelCalculatePremiumService {

    TravelGetAgreementCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command);

}
