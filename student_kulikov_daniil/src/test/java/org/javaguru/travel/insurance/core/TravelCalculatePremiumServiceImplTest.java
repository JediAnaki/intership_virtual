package org.javaguru.travel.insurance.core;

import lombok.val;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;


    private TravelCalculatePremiumRequest init() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Daniil");
        request.setPersonLastName("Kulikov");
        request.setAgreementDateFrom(LocalDateTime.of(2024, 2, 1, 12, 0));
        request.setAgreementDateTo(LocalDateTime.of(2024, 2, 10, 12, 0));
        return request;
    }

    @Test
    void shouldPopulatePersonFirstName() {
        //Arrange
        var request = init();
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(request)).thenReturn(List.of());

        //Act
        var response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }



    @Test
    void shouldPopulatePersonLastName() {
        //Arrange
        var request = init();
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(request)).thenReturn(List.of());

        //Act
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    void shouldPopulatePersonDateFrom() {
        //Arrange
        var request = init();
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(request)).thenReturn(List.of());

        //Act
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    void shouldPopulatePersonDateTo() {
        //Arrange
        var request = init();
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(request)).thenReturn(List.of());

        //Act
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    void shouldReturnResponseWithErrors() {
        //Arrange
        var request = new TravelCalculatePremiumRequest();
        ValidationError validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));

        //Act
        var response = premiumService.calculatePremium(request);

        //Assert
        assertTrue(response.hasErrors());
    }

    @Test
    void shouldReturnResponseWithCorrectErrorCount() {
        //Arrange
        var request = new TravelCalculatePremiumRequest();
        var errors = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(errors));

        //Act
        var response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    void shouldReturnResponseWithCorrectError() {
        //Arrange
        var request = new TravelCalculatePremiumRequest();
        var errors = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(errors));

        //Act
        var response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getErrors().getFirst().getField(), "field");
        assertEquals(response.getErrors().getFirst().getMessage(), "message");
    }

    @Test
    void allFieldsMustBeEmptyWhenResponseContainsError() {
        //Arrange
        var request = new TravelCalculatePremiumRequest();
        var errors = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(errors));

        //Act
        var response = premiumService.calculatePremium(request);

        //Assert
        assertNull(response.getPersonFirstName());
        assertNull(response.getPersonLastName());
        assertNull(response.getAgreementDateFrom());
        assertNull(response.getAgreementDateTo());
        assertNull(response.getAgreementPrice());
    }

    @Test
    public void shouldNOtBeInteractionWithDateTimeServiceWhenResponseContainsError() {
        //Arrange
        var request = new TravelCalculatePremiumRequest();
        var errors = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(errors));

        //Act
        var response = premiumService.calculatePremium(request);

        //Assert
        verifyNoInteractions(dateTimeService);
    }

}