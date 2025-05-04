package org.jedianakin.travel.insurance.core.validations.integration;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.jedianakin.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgreementDateFromValidationIntegrationTest {

    @Autowired private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateFromIsNull() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(null)
                .agreementDateTo(LocalDate.of(2030, 1, 1))
                .country("SPAIN")
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .persons(List.of(PersonDTO.builder()
                        .personFirstName("Vasja")
                        .personLastName("Pupkin")
                        .personCode("123456-12345")
                        .personBirthDate(LocalDate.of(2020, 1, 1))
                        .medicalRiskLimitLevel("LEVEL_10000")
                        .build())
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_2", errors.getFirst().getErrorCode());
        assertEquals("Field agreementDateFrom must not be empty!", errors.getFirst().getDescription());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(LocalDate.of(2020, 1, 1))
                .agreementDateTo(LocalDate.of(2030, 1, 1))
                .country("SPAIN")
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .persons(List.of(PersonDTO.builder()
                        .personFirstName("Vasja")
                        .personLastName("Pupkin")
                        .personCode("123456-12345")
                        .personBirthDate(LocalDate.of(2000, 1, 1))
                        .medicalRiskLimitLevel("LEVEL_10000")
                        .build())
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_1", errors.getFirst().getErrorCode());
        assertEquals("Field agreementDateFrom must be in the future!", errors.getFirst().getDescription());
    }
}
