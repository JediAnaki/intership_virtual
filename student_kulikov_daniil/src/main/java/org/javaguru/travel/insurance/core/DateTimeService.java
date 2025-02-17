package org.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
class DateTimeService {

    long getDaysBetween(LocalDate dateTime1, LocalDate dateTime2) {
        return ChronoUnit.DAYS.between(dateTime1, dateTime2);
    }

}
