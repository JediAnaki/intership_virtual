package org.jedianakin.travel.insurance.core.services;

import org.jedianakin.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.jedianakin.travel.insurance.core.api.command.TravelGetAgreementCoreResult;


public interface TravelGetAgreementService {

    TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command);
}
