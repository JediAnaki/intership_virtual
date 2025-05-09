package org.jedianakin.travel.insurance.rest.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jedianakin.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumRequestLoggerV1 {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerV1.class);

    void log(TravelCalculatePremiumRequestV1 request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }

}
