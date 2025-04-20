package org.javaguru.travel.insurance.rest.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLogger {

    private final static Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumResponseLogger.class);

    void log(TravelCalculatePremiumResponseV1 response) {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        try {
            String jsonRequest = objectMapper.writeValueAsString(response);
            logger.info("TravelCalculatePremiumRequest: {}", jsonRequest);
        } catch (JsonProcessingException e) {
            logger.error("Ошибка при сериализации запроса в JSON", e);
        }
    }
}
