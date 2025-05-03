package org.jedianakin.travel.insurance.core.underwriting.calculators.medical;

import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.domain.TMAgeCoefficient;
import org.jedianakin.travel.insurance.core.repositories.TMAgeCoefficientRepository;
import org.jedianakin.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TMTMAgeCoefficientCalculatorTest {

    private DateTimeUtil dateTimeUtil;
    private TMAgeCoefficientRepository TMAgeCoefficientRepository;

    private PersonDTO person;

    @BeforeEach
    void setUp() {
        dateTimeUtil = mock(DateTimeUtil.class);
        TMAgeCoefficientRepository = mock(TMAgeCoefficientRepository.class);

        person = new PersonDTO();
        person.setPersonBirthDate(LocalDate.of(1990, 1, 1));
    }

    @Test
    void shouldReturnOneWhenDisabled() {
        var calculator = new TMAgeCoefficientCalculator(false, dateTimeUtil, TMAgeCoefficientRepository);
        BigDecimal result = calculator.calculate(person);
        assertEquals(BigDecimal.ONE, result);
    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        var calculator = new TMAgeCoefficientCalculator(true, dateTimeUtil, TMAgeCoefficientRepository);
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;
        BigDecimal expectedCoefficient = BigDecimal.valueOf(1.2);

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(currentDate);
        TMAgeCoefficient TMAgeCoefficient = mock(TMAgeCoefficient.class);
        when(TMAgeCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        when(TMAgeCoefficientRepository.findCoefficient(age)).thenReturn(Optional.of(TMAgeCoefficient));

        BigDecimal result = calculator.calculate(person);

        assertEquals(expectedCoefficient, result);
    }

    @Test
    void shouldThrowExceptionWhenAgeCoefficientNotFound() {
        var calculator = new TMAgeCoefficientCalculator(true, dateTimeUtil, TMAgeCoefficientRepository);
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(currentDate);
        when(TMAgeCoefficientRepository.findCoefficient(age)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(person));

        assertEquals("Age coefficient not found for age = " + age, exception.getMessage());
    }

}