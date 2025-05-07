package org.jedianakin.blacklist.core.services;

import org.jedianakin.blacklist.core.api.BlackListedPersonCoreCommand;
import org.jedianakin.blacklist.core.api.BlackListedPersonCoreResult;

public interface BlackListedPersonService {

    BlackListedPersonCoreResult check(BlackListedPersonCoreCommand command);

}
