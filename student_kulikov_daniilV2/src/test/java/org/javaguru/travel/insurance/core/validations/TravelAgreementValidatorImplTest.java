package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelAgreementValidatorImplTest {

    @Test
    void shouldNotReturnErrors() {
        PersonDTO person = mock(PersonDTO.class);
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getPersons()).thenReturn(List.of(person));
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(agreement)).thenReturn(Optional.empty());
        when(validation1.validateList(agreement)).thenReturn(List.of());
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(agreement)).thenReturn(Optional.empty());
        when(validation2.validateList(agreement)).thenReturn(List.of());

        TravelPersonFieldValidation validation3 = mock(TravelPersonFieldValidation.class);
        when(validation3.validate(person)).thenReturn(Optional.empty());
        when(validation3.validateList(person)).thenReturn(List.of());
        TravelPersonFieldValidation validation4 = mock(TravelPersonFieldValidation.class);
        when(validation4.validate(person)).thenReturn(Optional.empty());
        when(validation4.validateList(person)).thenReturn(List.of());

        List<TravelAgreementFieldValidation> agreementFieldValidations = List.of(validation1, validation2);
        List<TravelPersonFieldValidation> personFieldValidations = List.of(validation3, validation4);
        var validator = new TravelAgreementValidatorImpl(agreementFieldValidations, personFieldValidations);

        List<ValidationErrorDTO> errorDTOS = validator.validate(agreement);
        assertTrue(errorDTOS.isEmpty());
    }

    @Test
    public void shouldReturnSingleAgreementErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO()));
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO()));

        List<TravelAgreementFieldValidation> agreementFieldValidations = List.of(validation1, validation2);
        List<TravelPersonFieldValidation> personFieldValidations = List.of();
        var validator = new TravelAgreementValidatorImpl(agreementFieldValidations, personFieldValidations);

        List<ValidationErrorDTO> errorDTOS = validator.validate(agreement);
        assertEquals(2, errorDTOS.size());
    }

    @Test
    public void shouldReturnSinglePersonErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(agreement.getPersons()).thenReturn(List.of(person));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(person)).thenReturn(Optional.of(new ValidationErrorDTO()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(person)).thenReturn(Optional.of(new ValidationErrorDTO()));

        List<TravelAgreementFieldValidation> agreementFieldValidations = List.of();
        List<TravelPersonFieldValidation> personFieldValidations = List.of(validation1, validation2);
        var validator = new TravelAgreementValidatorImpl(agreementFieldValidations, personFieldValidations);

        List<ValidationErrorDTO> errorDTOS = validator.validate(agreement);
        assertEquals(2, errorDTOS.size());
    }

    @Test
    public void shouldReturnListAgreementErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(agreement)).thenReturn(Optional.empty());
        when(validation1.validateList(agreement)).thenReturn(List.of(new ValidationErrorDTO()));
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(agreement)).thenReturn(Optional.empty());
        when(validation2.validateList(agreement)).thenReturn(List.of(new ValidationErrorDTO()));

        List<TravelAgreementFieldValidation> agreementFieldValidations = List.of(validation1, validation2);
        List<TravelPersonFieldValidation> personFieldValidations = List.of();
        var validator = new TravelAgreementValidatorImpl(agreementFieldValidations, personFieldValidations);

        List<ValidationErrorDTO> errorDTOS = validator.validate(agreement);
        assertEquals(2, errorDTOS.size());
    }

    @Test
    public void shouldReturnListPersonErrors() {
        PersonDTO person = mock(PersonDTO.class);
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getPersons()).thenReturn(List.of(person));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(person)).thenReturn(Optional.empty());
        when(validation1.validateList(person)).thenReturn(List.of(new ValidationErrorDTO()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(person)).thenReturn(Optional.empty());
        when(validation2.validateList(person)).thenReturn(List.of(new ValidationErrorDTO()));
        List<TravelAgreementFieldValidation> agreementValidations = List.of();
        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        var validator = new TravelAgreementValidatorImpl(agreementValidations, personValidations);
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 2);
    }
}
