package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TravelCalculatePremiumServiceImplTest {
    @Mock
    private final DateTimeService dateTimeService = mock(DateTimeService.class);
    private final TravelCalculatePremiumServiceImpl premiumService = new TravelCalculatePremiumServiceImpl(dateTimeService);

    private TravelCalculatePremiumRequest init() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Daniil");
        request.setPersonLastName("Kulikov");
        request.setAgreementDateFrom(LocalDateTime.of(2024, 2, 1, 12, 0));
        request.setAgreementDateTo(LocalDateTime.of(2024, 2, 10, 12, 0));
        return request;
    }

    @Test
    public void shouldPopulatePersonFirstName() {
        //Arrange
        TravelCalculatePremiumRequest request = init();

        //Act
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void shouldPopulatePersonLastName() {
        //Arrange
        TravelCalculatePremiumRequest request = init();

        //Act
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void shouldPopulatePersonDateFrom() {
        //Arrange
        TravelCalculatePremiumRequest request = init();

        //Act
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulatePersonDateTo() {
        //Arrange
        TravelCalculatePremiumRequest request = init();

        //Act
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);

        //Assert
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }
}