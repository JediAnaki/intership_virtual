package org.jedianakin.travel.insurance.rest.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jedianakin.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumResponseLoggerV1 {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumResponseLoggerV1.class);
    private final ObjectMapper objectMapper;

    TravelCalculatePremiumResponseLoggerV1(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    void log(TravelCalculatePremiumResponseV1 response) {
        try {
            String json = objectMapper.writeValueAsString(response);
            logger.info("RESPONSE: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert response to JSON", e);
        }
    }

}
