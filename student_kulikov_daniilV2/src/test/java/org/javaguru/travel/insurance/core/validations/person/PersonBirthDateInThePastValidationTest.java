package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonBirthDateInThePastValidationTest {

    @Mock private DateTimeUtil dateTimeUtil;
    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonBirthDateInThePastValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonBirthDateInTheFuture() {
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonBirthDate()).thenReturn(LocalDate.of(2030, 1, 1));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.of(2023, 1, 1));
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_12")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(person);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonBirthDateDateInThePast() {
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonBirthDate()).thenReturn(LocalDate.of(2020, 1, 1));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.of(2023, 1, 1));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(person);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }
}