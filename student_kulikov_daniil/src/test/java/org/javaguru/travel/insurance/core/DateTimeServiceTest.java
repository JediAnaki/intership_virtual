package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class DateTimeServiceTest {

    private final DateTimeService dateTimeService = new DateTimeService();

    private LocalDateTime[] creatTestDate() {
        LocalDateTime dateTime1 = LocalDateTime.of(2024, 10, 10, 3, 1);
        LocalDateTime dateTime2 = LocalDateTime.of(2024, 10, 10, 3, 1);
        return new LocalDateTime[]{dateTime1, dateTime2};

    }

    @Test
    public void shouldBetweenBeZero() {
        //Arrange
        LocalDateTime[] dates = creatTestDate();
        LocalDateTime dateTime1 = dates[0];
        LocalDateTime dateTime2 = dates[1];

        //Act
        long daysBetween = dateTimeService.getDaysBetween(dateTime1, dateTime2);

        //Assert
        assertEquals(0L, daysBetween);
    }

    @Test
    public void shouldReturnPositiveDaysDifference() {
        // Arrange
        LocalDateTime[] dates = creatTestDate();
        LocalDateTime dateTime1 = dates[0];
        LocalDateTime dateTime2 = dateTime1.plusDays(1); // Изменяем второй объект

        // Act
        long daysBetween = dateTimeService.getDaysBetween(dateTime1, dateTime2);

        // Assert
        assertEquals(1, daysBetween);
    }

    @Test
    public void shouldReturnNegativeDaysDifference() {
        //Arrange
        LocalDateTime[] dates = creatTestDate();
        LocalDateTime dateTime1 = dates[0];
        LocalDateTime dateTime2 = dateTime1.minusDays(1);

        //Act
        long daysBetween = dateTimeService.getDaysBetween(dateTime1, dateTime2);

        //Assert
        assertEquals(-1L, daysBetween);
    }


}
