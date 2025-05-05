package org.jedianakin.travel.insurance.core.api.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class AgreementDTO {

    private String uuid;

    private LocalDate agreementDateFrom;

    private LocalDate agreementDateTo;

    private String country;

    private List<String> selectedRisks;

    private List<PersonDTO> persons;

    private BigDecimal agreementPremium;

}
