package org.jedianakin.travel.insurance.core.validations;

import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelAgreementUuidValidator {

    List<ValidationErrorDTO> validate(String uuid);

}
