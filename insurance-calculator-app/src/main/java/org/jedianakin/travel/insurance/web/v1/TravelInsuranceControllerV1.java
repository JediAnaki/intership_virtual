package org.jedianakin.travel.insurance.web.v1;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedianakin.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.jedianakin.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.jedianakin.travel.insurance.core.services.TravelCalculatePremiumService;
import org.jedianakin.travel.insurance.dto.v1.DtoV1Converter;
import org.jedianakin.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.jedianakin.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelInsuranceControllerV1 {

    private final TravelCalculatePremiumService service;
    private final DtoV1Converter dtoV1Converter;

    @GetMapping("/insurance/travel/web/v1")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV1());
        modelMap.addAttribute("response", new TravelCalculatePremiumResponseV1());
        return "travel-calculate-premium-v1";
    }

    @PostMapping("/insurance/travel/web/v1")
    public String processForm(@ModelAttribute(value = "request") TravelCalculatePremiumRequestV1 request,
                              ModelMap modelMap) {
        TravelCalculatePremiumCoreCommand coreCommand = dtoV1Converter.buildCoreCommand(request);
        TravelCalculatePremiumCoreResult coreResult = service.calculatePremium(coreCommand);
        TravelCalculatePremiumResponseV1 response = dtoV1Converter.buildResponse(coreResult);
        modelMap.addAttribute("response", response);
        return "travel-calculate-premium-v1";
    }

}
