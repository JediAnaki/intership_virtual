package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;

    @Test
    public void successRequest() throws Exception {
        executeAndCompare(
                "rest/Request/TravelCalculatePremiumRequest_success.json",
                "rest/Response/TravelCalculatePremiumResponse_success.json"
        );
    }

    @Test
    public void firstNameNotProvided() throws Exception {
        executeAndCompare(
                "rest/Request/TravelCalculatePremiumRequest_firstName_not_provided.json",
                "rest/Response/TravelCalculatePremiumResponse_firstName_not_provided.json"
        );
    }

    @Test
    public void lastNameNotProvided() throws Exception {
        executeAndCompare(
                "rest/Request/TravelCalculatePremiumRequest_lastName_not_provided.json",
                "rest/Response/TravelCalculatePremiumResponse_lastName_not_provided.json"
        );
    }

    @Test
    public void agreementDateFromNotProvided() throws Exception {
        executeAndCompare(
                "rest/Request/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json",
                "rest/Response/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json"
        );
    }

    @Test
    public void selectedRisksIsNull() throws Exception {
        executeAndCompare(
                "rest/Request/TravelCalculatePremiumRequest_selectedRisks_null.json",
                "rest/Response/TravelCalculatePremiumResponse_selectedRisks_null.json"
        );
    }

    @Test
    public void selectedRisksIsEmpty() throws Exception {
        executeAndCompare(
                "rest/Request/TravelCalculatePremiumRequest_selectedRisks_empty.json",
                "rest/Response/TravelCalculatePremiumResponse_selectedRisks_empty.json"
        );
    }

    @Test
    public void agreementDateToNotProvided() throws Exception {
        executeAndCompare(
                "rest/Request/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json",
                "rest/Response/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json"
        );
    }

    @Test
    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
        executeAndCompare(
                "rest/Request/TravelCalculatePremiumRequest_dateTo_lessThen_dateFrom.json",
                "rest/Response/TravelCalculatePremiumResponse_dateTo_lessThen_dateFrom.json"
        );
    }

    @Test
    public void allFieldsNotProvided() throws Exception {
        executeAndCompare(
                "rest/Request/TravelCalculatePremiumRequest_allFields_not_provided.json",
                "rest/Response/TravelCalculatePremiumResponse_allFields_not_provided.json"
        );
    }

    private void executeAndCompare(String jsonRequestFilePath,
                                   String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);

        JSONAssert.assertEquals(responseBodyContent,
                                    jsonResponse,
                                JSONCompareMode.LENIENT);

    }
}
