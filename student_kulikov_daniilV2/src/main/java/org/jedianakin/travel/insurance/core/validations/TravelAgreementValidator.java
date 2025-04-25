package org.jedianakin.travel.insurance.core.validations;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelAgreementValidator {

    List<ValidationErrorDTO> validate(AgreementDTO agreement);

}
