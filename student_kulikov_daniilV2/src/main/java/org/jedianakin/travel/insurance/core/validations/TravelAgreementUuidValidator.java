package org.jedianakin.travel.insurance.core.validations;

import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TravelAgreementUuidValidator {

    List<ValidationErrorDTO> validate(String uuid);

}
