package org.javaguru.travel.insurance.core.core.underwriting;

import org.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwritingImpl;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {

    @Mock private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private TravelCalculatePremiumUnderwritingImpl premiumUnderwriting;

    @Test
    void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2026, 1, 1));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2026, 10, 1));
        when(dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(9L);
        BigDecimal premium = premiumUnderwriting.calculatePremium(request);
        assertEquals(new BigDecimal(9), premium);
    }

}