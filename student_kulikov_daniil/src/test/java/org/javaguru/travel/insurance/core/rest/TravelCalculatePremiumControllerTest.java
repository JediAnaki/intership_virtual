package org.javaguru.travel.insurance.core.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;


import java.io.UnsupportedEncodingException;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;

    @Test
    @DisplayName("Test 3: allFields is OK 200")
    public void allFieldsIsOkControllerTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_success.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_success.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }


    @Test
    @DisplayName("Test 2: allFields null")
    public void allFieldsNullControllerTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_allFields_not_provided.json"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_allFields_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));

    }


    @Test
    @DisplayName("Test 4: dateFrom > dateTo")
    public void dateToInExcessOfDateFrom() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_dateTo_lessThen_dateFrom.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_dateTo_lessThen_dateFrom.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test 1: firstName null")
    public void fieldFirstNameUnAppreciate() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_firstName_not_provided.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_firstName_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test 1.2: lastName null")
    public void fieldLastNameUnAppreciate() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_lastName_not_provided.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_lastName_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test 1.3: agreementDateFrom null")
    public void fieldAgreementDateFromUnAppreciate() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test 1.4: agreementDateTo null")
    public void fieldAgreementDateToUnAppreciate() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }
}
