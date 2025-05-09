package org.jedianakin.travel.insurance.rest.internal;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.jedianakin.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import org.jedianakin.travel.insurance.core.services.TravelGetAgreementService;
import org.jedianakin.travel.insurance.dto.internal.GetAgreementDtoConverter;
import org.jedianakin.travel.insurance.dto.internal.TravelGetAgreementResponse;
import org.jedianakin.travel.insurance.rest.common.TravelRestRequestExecutionTimeLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RequestMapping("/insurance/travel/api/internal/agreement")
public class TravelGetAgreementRestController {

	private final TravelGetAgreementRequestLogger requestLogger;
	private final TravelGetAgreementResponseLogger responseLogger;
	private final TravelRestRequestExecutionTimeLogger executionTimeLogger;
	private final TravelGetAgreementService getAgreementService;
	private final GetAgreementDtoConverter dtoConverter;

	@GetMapping(path = "/{uuid}",
			produces = "application/json")
	public TravelGetAgreementResponse getAgreement(@PathVariable("uuid") String uuid) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelGetAgreementResponse response = processRequest(uuid);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelGetAgreementResponse processRequest(String uuid) {
		requestLogger.log(uuid);
		TravelGetAgreementCoreCommand coreCommand = dtoConverter.buildCoreCommand(uuid);
		TravelGetAgreementCoreResult coreResult = getAgreementService.getAgreement(coreCommand);
		TravelGetAgreementResponse response = dtoConverter.buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}