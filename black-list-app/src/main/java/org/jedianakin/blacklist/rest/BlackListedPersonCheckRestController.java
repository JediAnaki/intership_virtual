package org.jedianakin.blacklist.rest;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.blacklist.core.api.BlackListedPersonCoreCommand;
import org.jedianakin.blacklist.core.api.BlackListedPersonCoreResult;
import org.jedianakin.blacklist.core.services.BlackListedPersonService;
import org.jedianakin.blacklist.dto.BlackListedPersonCheckRequest;
import org.jedianakin.blacklist.dto.BlackListedPersonCheckResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RequestMapping("/blacklist/person/check")
public class BlackListedPersonCheckRestController {

    private final BlackListedPersonRequestLogger requestLogger;
    private final BlackListedPersonResponseLogger responseLogger;
    private final RestRequestExecutionTimeLogger executionTimeLogger;
    private final DtoConverter dtoConverter;
    private final BlackListedPersonService blackListedPersonService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public BlackListedPersonCheckResponse calculatePremium(@RequestBody BlackListedPersonCheckRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        BlackListedPersonCheckResponse response = processRequest(request);
        executionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }

    private BlackListedPersonCheckResponse processRequest(BlackListedPersonCheckRequest request) {
        requestLogger.log(request);
        BlackListedPersonCoreCommand coreCommand = dtoConverter.buildCoreCommand(request);
        BlackListedPersonCoreResult coreResult = blackListedPersonService.check(coreCommand);
        BlackListedPersonCheckResponse response = dtoConverter.buildResponse(coreResult);
        responseLogger.log(response);
        return response;
    }

}
