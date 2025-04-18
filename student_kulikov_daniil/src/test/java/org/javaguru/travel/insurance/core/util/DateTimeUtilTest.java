package org.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

class DateTimeUtilTest {

    private final DateTimeUtil dateTimeUtil = new DateTimeUtil();

    private LocalDate[] creatTestDate() {
        LocalDate dateTime1 = LocalDate.of(2024, 10, 10);
        LocalDate dateTime2 = LocalDate.of(2024, 10, 10);
        return new LocalDate[]{dateTime1, dateTime2};

    }

    @Test
    void shouldBetweenBeZero() {
        //Arrange
        LocalDate[] dates = creatTestDate();
        LocalDate dateTime1 = dates[0];
        LocalDate dateTime2 = dates[1];

        //Act
        long daysBetween = dateTimeUtil.getDaysBetween(dateTime1, dateTime2);

        //Assert
        assertEquals(0L, daysBetween);
    }

    @Test
    void shouldReturnPositiveDaysDifference() {
        // Arrange
        LocalDate[] dates = creatTestDate();
        LocalDate dateTime1 = dates[0];
        LocalDate dateTime2 = dateTime1.plusDays(1);

        // Act
        long daysBetween = dateTimeUtil.getDaysBetween(dateTime1, dateTime2);

        // Assert
        assertEquals(1, daysBetween);
    }

    @Test
    void shouldReturnNegativeDaysDifference() {
        //Arrange
        LocalDate[] dates = creatTestDate();
        LocalDate dateTime1 = dates[0];
        LocalDate dateTime2 = dateTime1.minusDays(1);

        //Act
        long daysBetween = dateTimeUtil.getDaysBetween(dateTime1, dateTime2);

        //Assert
        assertEquals(-1L, daysBetween);
    }
}
