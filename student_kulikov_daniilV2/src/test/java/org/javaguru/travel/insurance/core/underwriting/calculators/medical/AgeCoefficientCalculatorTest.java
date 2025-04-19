package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AgeCoefficientCalculatorTest {

    private DateTimeUtil dateTimeUtil;
    private AgeCoefficientRepository ageCoefficientRepository;

    private PersonDTO person;

    @BeforeEach
    void setUp() {
        dateTimeUtil = mock(DateTimeUtil.class);
        ageCoefficientRepository = mock(AgeCoefficientRepository.class);

        person = new PersonDTO();
        person.setPersonBirthDate(LocalDate.of(1990, 1, 1));
    }

    @Test
    void shouldReturnOneWhenDisabled() {
        var calculator = new AgeCoefficientCalculator(false, dateTimeUtil, ageCoefficientRepository);
        BigDecimal result = calculator.calculate(person);
        assertEquals(BigDecimal.ONE, result);
    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        var calculator = new AgeCoefficientCalculator(true, dateTimeUtil, ageCoefficientRepository);
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;
        BigDecimal expectedCoefficient = BigDecimal.valueOf(1.2);

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(currentDate);
        AgeCoefficient ageCoefficient = mock(AgeCoefficient.class);
        when(ageCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.of(ageCoefficient));

        BigDecimal result = calculator.calculate(person);

        assertEquals(expectedCoefficient, result);
    }

    @Test
    void shouldThrowExceptionWhenAgeCoefficientNotFound() {
        var calculator = new AgeCoefficientCalculator(true, dateTimeUtil, ageCoefficientRepository);
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(currentDate);
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(person));

        assertEquals("Age coefficient not found for age = " + age, exception.getMessage());
    }

}