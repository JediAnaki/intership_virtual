package org.jedianakin.travel.insurance.rest.v2;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.jedianakin.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.jedianakin.travel.insurance.core.services.TravelCalculatePremiumService;
import org.jedianakin.travel.insurance.dto.v2.DtoV2Converter;
import org.jedianakin.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.jedianakin.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.jedianakin.travel.insurance.rest.common.TravelRestRequestExecutionTimeLogger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RequestMapping("/insurance/travel/api/v2")
public class TravelCalculatePremiumRestControllerV2 {

	private final TravelCalculatePremiumRequestLoggerV2 requestLogger;
	private final TravelCalculatePremiumResponseLoggerV2 responseLogger;
	private final TravelRestRequestExecutionTimeLogger executionTimeLogger;
	private final TravelCalculatePremiumService calculatePremiumService;
	private final DtoV2Converter dtoV2Converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV2 response = processRequest(request);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelCalculatePremiumResponseV2 processRequest(TravelCalculatePremiumRequestV2 request) {
		requestLogger.log(request);
		TravelCalculatePremiumCoreCommand coreCommand = dtoV2Converter.buildCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV2 response = dtoV2Converter.buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}