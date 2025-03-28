package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl validator;

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());

        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());

        List<TravelRequestValidation> travelValidationOptionals = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "travelValidationOptionals", travelValidationOptionals);
        ReflectionTestUtils.setField(validator, "travelValidationLists", List.of());

        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.of(new ValidationError("errorCode", "description")));

        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.of(new ValidationError("errorCode", "description")));

        List<TravelRequestValidation> travelValidationOptionals = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "travelValidationOptionals", travelValidationOptionals);
        ReflectionTestUtils.setField(validator, "travelValidationLists", List.of());

        List<ValidationError> errors = validator.validate(request);
        assertEquals(2, errors.size());
    }
}