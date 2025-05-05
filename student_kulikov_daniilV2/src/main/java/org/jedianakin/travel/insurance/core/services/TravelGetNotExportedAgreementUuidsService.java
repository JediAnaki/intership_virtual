package org.jedianakin.travel.insurance.core.services;

import org.jedianakin.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import org.jedianakin.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;

public interface TravelGetNotExportedAgreementUuidsService {

    TravelGetNotExportedAgreementUuidsCoreResult getAgreementUuids(TravelGetNotExportedAgreementUuidsCoreCommand command);

}
