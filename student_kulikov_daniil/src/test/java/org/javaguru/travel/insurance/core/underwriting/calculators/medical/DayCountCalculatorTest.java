package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DayCountCalculatorTest {

    @Mock private DateTimeUtil dateTimeUtil;

    @InjectMocks private DayCountCalculator dayCountCalculator;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.of(2020, 1, 1));
        request.setAgreementDateTo(LocalDate.of(2020, 1, 10));
    }

    @Test
    void shouldCalculateDayCountCorrectly() {
        long expectedDays = 9;
        when(dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(expectedDays);
        BigDecimal result = dayCountCalculator.calculate(request);
        assertEquals(BigDecimal.valueOf(expectedDays), result);
    }
}
