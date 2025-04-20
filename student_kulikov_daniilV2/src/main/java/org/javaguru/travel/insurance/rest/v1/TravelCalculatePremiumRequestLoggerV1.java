package org.javaguru.travel.insurance.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumRequestLoggerV1 {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerV1.class);

    void log(TravelCalculatePremiumRequestV1 request) {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        try {
            String jsonRequest = objectMapper.writeValueAsString(request);
            logger.info("TravelCalculatePremiumRequest: {}", jsonRequest);
        } catch (Exception e) {
            logger.error("Ошибка при сериализации запроса в JSON", e);
        }
    }
}
