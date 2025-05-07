package org.jedianakin.travel.insurance.core.blacklist;

import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;

public interface BlackListPersonCheckService {

    boolean isPersonBlacklisted(PersonDTO personDTO);

}
