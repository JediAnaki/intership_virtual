package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TravelCalculatePremiumRequestV1ValidatorTest {

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        // Моки для одиночных валидаций
        TravelRequestValidation singleValidation1 = mock(TravelRequestValidation.class);
        when(singleValidation1.validate(request)).thenReturn(Optional.empty());
        TravelRequestValidation singleValidation2 = mock(TravelRequestValidation.class);
        when(singleValidation2.validate(request)).thenReturn(Optional.empty());

        // Моки для пакетных валидаций
        TravelRequestBatchValidation batchValidation1 = mock(TravelRequestBatchValidation.class);
        when(batchValidation1.validateList(request)).thenReturn(List.of());
        TravelRequestBatchValidation batchValidation2 = mock(TravelRequestBatchValidation.class);
        when(batchValidation2.validateList(request)).thenReturn(List.of());

        TravelCalculatePremiumRequestValidatorImpl validator =
                new TravelCalculatePremiumRequestValidatorImpl(
                        List.of(singleValidation1, singleValidation2),
                        List.of(batchValidation1, batchValidation2)
                );

        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSingleErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        // Моки для одиночных валидаций, возвращающие ошибки
        TravelRequestValidation singleValidation1 = mock(TravelRequestValidation.class);
        when(singleValidation1.validate(request))
                .thenReturn(Optional.of(new ValidationError("errorCode1", "errorMessage1")));
        TravelRequestValidation singleValidation2 = mock(TravelRequestValidation.class);
        when(singleValidation2.validate(request))
                .thenReturn(Optional.of(new ValidationError("errorCode2", "errorMessage2")));

        // Пакетные валидации без ошибок
        TravelRequestBatchValidation batchValidation1 = mock(TravelRequestBatchValidation.class);
        when(batchValidation1.validateList(request)).thenReturn(List.of());
        TravelRequestBatchValidation batchValidation2 = mock(TravelRequestBatchValidation.class);
        when(batchValidation2.validateList(request)).thenReturn(List.of());

        TravelCalculatePremiumRequestValidatorImpl validator =
                new TravelCalculatePremiumRequestValidatorImpl(
                        List.of(singleValidation1, singleValidation2),
                        List.of(batchValidation1, batchValidation2)
                );

        List<ValidationError> errors = validator.validate(request);
        assertEquals(2, errors.size());
    }

    @Test
    public void shouldReturnListErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        // Одиночные валидации без ошибок
        TravelRequestValidation singleValidation1 = mock(TravelRequestValidation.class);
        when(singleValidation1.validate(request)).thenReturn(Optional.empty());
        TravelRequestValidation singleValidation2 = mock(TravelRequestValidation.class);
        when(singleValidation2.validate(request)).thenReturn(Optional.empty());

        // Пакетные валидации, возвращающие ошибки
        TravelRequestBatchValidation batchValidation1 = mock(TravelRequestBatchValidation.class);
        when(batchValidation1.validateList(request))
                .thenReturn(List.of(new ValidationError("errorCode1", "errorMessage1")));
        TravelRequestBatchValidation batchValidation2 = mock(TravelRequestBatchValidation.class);
        when(batchValidation2.validateList(request))
                .thenReturn(List.of(new ValidationError("errorCode2", "errorMessage2")));

        TravelCalculatePremiumRequestValidatorImpl validator =
                new TravelCalculatePremiumRequestValidatorImpl(
                        List.of(singleValidation1, singleValidation2),
                        List.of(batchValidation1, batchValidation2)
                );

        List<ValidationError> errors = validator.validate(request);
        assertEquals(2, errors.size());
    }

    @Test
    public void shouldReturnBothSingleAndListErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        // Одиночные валидации с ошибками
        TravelRequestValidation singleValidation = mock(TravelRequestValidation.class);
        when(singleValidation.validate(request))
                .thenReturn(Optional.of(new ValidationError("singleError", "Ошибка одиночной валидации")));

        // Пакетная валидация с ошибкой
        TravelRequestBatchValidation batchValidation = mock(TravelRequestBatchValidation.class);
        when(batchValidation.validateList(request))
                .thenReturn(List.of(new ValidationError("listError", "Ошибка пакетной валидации")));

        TravelCalculatePremiumRequestValidatorImpl validator =
                new TravelCalculatePremiumRequestValidatorImpl(
                        List.of(singleValidation),
                        List.of(batchValidation)
                );

        List<ValidationError> errors = validator.validate(request);
        // Ожидаем общее количество ошибок = 2
        assertEquals(2, errors.size());
    }
}
