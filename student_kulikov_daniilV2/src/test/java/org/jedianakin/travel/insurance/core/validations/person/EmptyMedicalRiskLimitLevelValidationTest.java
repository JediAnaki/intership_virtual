package org.jedianakin.travel.insurance.core.validations.person;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.jedianakin.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmptyMedicalRiskLimitLevelValidationTest {

    private ValidationErrorFactory errorFactory;

    private AgreementDTO agreement;
    private PersonDTO person;

    @BeforeEach
    void setUp() {
        agreement = new AgreementDTO();
        person = new PersonDTO();
        errorFactory = mock(ValidationErrorFactory.class);
    }

    @Test
    void shouldReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndNullOrBlank() {
        agreement.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        person.setMedicalRiskLimitLevel(null);
        ValidationErrorDTO expectedError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_13")).thenReturn(expectedError);

        var validation = new EmptyMedicalRiskLimitLevelValidation(true, errorFactory);

        Optional<ValidationErrorDTO> result = validation.validate(agreement, person);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndIsNotBlank() {
        agreement.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        person.setMedicalRiskLimitLevel("LEVEL_10000");
        var validation = new EmptyMedicalRiskLimitLevelValidation(true, errorFactory);
        Optional<ValidationErrorDTO> result = validation.validate(agreement, person);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelNotEnabledAndIsBlank() {
        agreement.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        person.setMedicalRiskLimitLevel("");
        var validation = new EmptyMedicalRiskLimitLevelValidation(false, errorFactory);
        Optional<ValidationErrorDTO> result = validation.validate(agreement, person);
        assertTrue(result.isEmpty());
    }

}