package org.jedianakin.blacklist.core.validations;

import org.jedianakin.blacklist.core.api.BlackListedPersonDTO;
import org.jedianakin.blacklist.core.api.ValidationErrorDTO;

import java.util.List;

public interface BlackListedPersonValidator {

    List<ValidationErrorDTO> validate(BlackListedPersonDTO personDTO);

}
