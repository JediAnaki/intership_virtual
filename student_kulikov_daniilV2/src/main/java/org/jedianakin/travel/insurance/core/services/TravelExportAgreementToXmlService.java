package org.jedianakin.travel.insurance.core.services;

import org.jedianakin.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreCommand;
import org.jedianakin.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreResult;

public interface TravelExportAgreementToXmlService {

    TravelExportAgreementToXmlCoreResult export(TravelExportAgreementToXmlCoreCommand command);

}
