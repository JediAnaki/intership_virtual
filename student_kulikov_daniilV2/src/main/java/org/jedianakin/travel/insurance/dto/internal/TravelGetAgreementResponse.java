package org.jedianakin.travel.insurance.dto.internal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jedianakin.travel.insurance.dto.CoreResponse;
import org.jedianakin.travel.insurance.dto.ValidationError;
import org.jedianakin.travel.insurance.dto.util.BigDecimalSerializer;
import org.jedianakin.travel.insurance.dto.v2.PersonResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelGetAgreementResponse extends CoreResponse {

    private String uuid;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate agreementDateFrom;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate agreementDateTo;

    private String country;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;

    @JsonAlias("persons")
    private List<PersonResponseDTO> persons;

    public TravelGetAgreementResponse(List<ValidationError> errors) {
        super(errors);
    }

}
