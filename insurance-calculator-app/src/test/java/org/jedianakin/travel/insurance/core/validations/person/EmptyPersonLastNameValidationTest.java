package org.jedianakin.travel.insurance.core.validations.person;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;
import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.jedianakin.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptyPersonLastNameValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyPersonLastNameValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonLastName()).thenReturn(null);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonLastName()).thenReturn("");
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsPresent() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonLastName()).thenReturn("Petrov");
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

}