package org.javaguru.travel.insurance.core;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class DateTimeService {

    long getDaysBetween(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return ChronoUnit.DAYS.between(dateTime1, dateTime2);
    }

}
