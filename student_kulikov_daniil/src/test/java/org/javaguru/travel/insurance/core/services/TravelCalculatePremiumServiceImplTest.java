package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock private TravelPremiumUnderwriting premiumUnderwriting;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    public void shouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("field", response.getErrors().getFirst().errorCode());
        assertEquals("errorMessage", response.getErrors().getFirst().description());
    }

    @Test
    public void shouldNotInvokeDateTimeUtilWhenValidationErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("field", response.getErrors().getFirst().errorCode());
        assertEquals("errorMessage", response.getErrors().getFirst().description());
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonFirstName() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals("personFirstName", response.getPersonFirstName());
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonLastName() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals("personLastName", response.getPersonLastName());
    }

//    @Test
//    public void shouldReturnResponseWithCorrectAgreementDateFrom() {
//        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
//        LocalDate dateFrom = new LocalDate();
//        when(request.getAgreementDateFrom()).thenReturn(dateFrom);
//        when(requestValidator.validate(request)).thenReturn(List.of());
//        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
//        when(premiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
//        TravelCalculatePremiumResponse response = service.calculatePremium(request);
//        assertEquals(response.getAgreementDateFrom(), dateFrom);
//    }
//
//    @Test
//    public void shouldReturnResponseWithCorrectAgreementDateTo() {
//        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
//        LocalDate dateTo = new LocalDate();
//        when(request.getAgreementDateTo()).thenReturn(dateTo);
//        when(requestValidator.validate(request)).thenReturn(List.of());
//        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
//        when(premiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
//        TravelCalculatePremiumResponse response = service.calculatePremium(request);
//        assertEquals(response.getAgreementDateTo(), dateTo);
//    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2023, 1 ,1));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2023, 1, 10));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult premiumCalculationResult = new TravelPremiumCalculationResult(new BigDecimal(9), null);
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(premiumCalculationResult);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(new BigDecimal(9), response.getAgreementPremium());
    }

    private List<ValidationError> buildValidationErrorList() {
        return List.of(
                new ValidationError("field", "errorMessage")
        );
    }
}