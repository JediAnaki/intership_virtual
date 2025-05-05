package org.jedianakin.travel.insurance.core.underwriting.calculators.cancellation;

import org.jedianakin.travel.insurance.core.api.dto.PersonDTO;
import org.jedianakin.travel.insurance.core.domain.TCAgeCoefficient;
import org.jedianakin.travel.insurance.core.repositories.TCAgeCoefficientRepository;
import org.jedianakin.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TCAgeCoefficientCalculatorTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock private TCAgeCoefficientRepository ageCoefficientRepository;

    @InjectMocks
    private TCAgeCoefficientCalculator calculator;

    private PersonDTO person;

    @BeforeEach
    void setUp() {
        person = new PersonDTO();
        person.setPersonBirthDate(LocalDate.of(1990, 1, 1));
    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;
        BigDecimal expectedCoefficient = BigDecimal.valueOf(1.2);

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(currentDate);
        TCAgeCoefficient ageCoefficient = mock(TCAgeCoefficient.class);
        when(ageCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.of(ageCoefficient));

        BigDecimal result = calculator.calculate(person);

        assertEquals(expectedCoefficient, result);
    }

    @Test
    void shouldThrowExceptionWhenAgeCoefficientNotFound() {
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(currentDate);
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(person));

        assertEquals("Age coefficient not found for age = " + age, exception.getMessage());
    }

}