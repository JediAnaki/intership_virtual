package org.jedianakin.travel.insurance.core.underwriting.integration.medical;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.jedianakin.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.jedianakin.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreement;
import static org.jedianakin.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"medical.risk.limit.level.enabled=false"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class LimitLevelSwitchDisabledIntegrationTest {

    @Autowired private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void shouldBeDisabledMedicalRiskLimitLevel() {
        PersonDTO person = createPersonDTO()
                .withFirstName("Vasja")
                .withLastName("Pupkin")
                .withBirthDate(LocalDate.of(2000, 1, 1))
                .withMedicalRiskLimitLevel("LEVEL_20000")
                .build();

        AgreementDTO agreement = createAgreement()
                .withDateFrom(LocalDate.of(2030, 1, 1))
                .withDateTo(LocalDate.of(2030, 5, 1))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(person)
                .build();

        TravelPremiumCalculationResult result = premiumUnderwriting.calculatePremium(agreement, person);

        assertEquals(result.totalPremium(), new BigDecimal("327.25"));
    }
}
