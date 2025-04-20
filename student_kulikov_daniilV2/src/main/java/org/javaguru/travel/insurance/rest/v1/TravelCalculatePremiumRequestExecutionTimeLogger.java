package org.javaguru.travel.insurance.rest.v1;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);

    void logTime(Stopwatch stopwatch) {
        stopwatch.stop();
        long resultTime = stopwatch.elapsed().toMillis();
        logger.info("Request processing time (ms): {}", resultTime);
    }
}
