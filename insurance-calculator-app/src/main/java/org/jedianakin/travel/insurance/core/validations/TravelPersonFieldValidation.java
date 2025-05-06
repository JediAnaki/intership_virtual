package org.jedianakin.travel.insurance.core.validations;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelPersonFieldValidation {

    Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person);

    List<ValidationErrorDTO> validateList(AgreementDTO agreement, PersonDTO person);

}
