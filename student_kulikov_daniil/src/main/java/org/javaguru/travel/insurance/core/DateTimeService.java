package org.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
class DateTimeService {

    long getDaysBetween(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return ChronoUnit.DAYS.between(dateTime1, dateTime2);
    }

}
