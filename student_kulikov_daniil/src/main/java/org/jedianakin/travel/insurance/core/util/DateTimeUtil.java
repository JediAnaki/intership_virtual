package org.jedianakin.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeUtil {

    public long getDaysBetween(LocalDate dateTime1, LocalDate dateTime2) {
        return ChronoUnit.DAYS.between(dateTime1, dateTime2);
    }

    public LocalDate getCurrentDateTime() {
        return LocalDate.now();
    }

}
