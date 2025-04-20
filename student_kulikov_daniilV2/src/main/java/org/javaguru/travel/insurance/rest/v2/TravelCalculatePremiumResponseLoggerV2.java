package org.javaguru.travel.insurance.rest.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLoggerV2 {

    private final static Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumResponseLoggerV2.class);

    void log(TravelCalculatePremiumResponseV2 response) {
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
