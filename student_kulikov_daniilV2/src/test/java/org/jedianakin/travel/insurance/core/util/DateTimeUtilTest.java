package org.jedianakin.travel.insurance.core.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeUtilTest {

    private DateTimeUtil dateTimeUtil = new DateTimeUtil();

    @Test
    public void shouldDaysBetweenBeZero() {
        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 1, 1);
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 0L);
    }

    @Test
    public void shouldDaysBetweenBePositive() {
        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 1, 10);
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 9L);
    }

    @Test
    public void shouldDaysBetweenBeNegative() {
        LocalDate date1 = LocalDate.of(2023, 1, 10);
        LocalDate date2 = LocalDate.of(2023, 1, 1);
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(daysBetween, -9L);
    }
}