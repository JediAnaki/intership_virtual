package org.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

    private final TravelCalculatePremiumRequestLogger requestLogger;
    private final TravelCalculatePremiumResponseLogger responseLogger;
    private final TravelCalculatePremiumService calculatePremiumService;
    private final TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;

    TravelCalculatePremiumController(TravelCalculatePremiumRequestLogger requestLogger,
                                     TravelCalculatePremiumResponseLogger responseLogger,
                                     TravelCalculatePremiumService calculatePremiumService,
                                     TravelCalculatePremiumRequestExecutionTimeLogger timeLogger) {
        this.requestLogger = requestLogger;
        this.responseLogger = responseLogger;
        this.calculatePremiumService = calculatePremiumService;
        this.timeLogger = timeLogger;

    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumResponse response = extracted(request);
        timeLogger.logTime(stopwatch);
        return response;
    }

    private TravelCalculatePremiumResponse extracted(TravelCalculatePremiumRequest request) {
        requestLogger.log(request);
        TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
        responseLogger.log(response);

        return response;
    }

}