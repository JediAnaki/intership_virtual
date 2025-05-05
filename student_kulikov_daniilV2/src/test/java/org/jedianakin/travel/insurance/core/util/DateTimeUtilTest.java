package org.jedianakin.travel.insurance.core.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeUtilTest {

    private DateTimeUtil dateTimeUtil = new DateTimeUtil();

    @Test
    public void shouldDaysBetweenBeZero() {
        LocalDate date1 = LocalDate.of(2023,1,1);
        LocalDate date2 = LocalDate.of(2023,1,1);
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(0L, daysBetween);
    }

    @Test
    public void shouldDaysBetweenBePositive() {
        LocalDate date1 = LocalDate.of(2023,1,1);
        LocalDate date2 = LocalDate.of(2023,1,10);
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(9L, daysBetween);
    }

    @Test
    public void shouldDaysBetweenBeNegative() {
        LocalDate date1 = LocalDate.of(2023,1,10);
        LocalDate date2 = LocalDate.of(2023,1,1);
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(-9L, daysBetween);
    }

}