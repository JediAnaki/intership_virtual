package org.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(pattern = "yyyy-MM-dd")
public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;
    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;

}
