package org.jedianakin.travel.insurance.core.api.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    @Size(max = 200, message = "Имя не должно превышать 200 символов")
    private String personFirstName;

    @Size(max = 200, message = "Имя не должно превышать 200 символов")
    private String personLastName;

    private String personCode;

    private LocalDate personBirthDate;

    private String medicalRiskLimitLevel;

    private BigDecimal travelCost;

    private List<RiskDTO> risks;

}
