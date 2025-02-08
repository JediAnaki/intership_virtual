package org.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private LocalDateTime agreementDateFrom;
    private LocalDateTime agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(String personFirstName, String personLastName, LocalDateTime agreementDateFrom, LocalDateTime agreementDateTo, BigDecimal agreementPrice) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;
        this.agreementPrice = agreementPrice;
    }

    public BigDecimal getAgreementPrice() {
        return agreementPrice;
    }

    public void setAgreementPrice(BigDecimal agreementPrice) {
        this.agreementPrice = agreementPrice;
    }

    public TravelCalculatePremiumResponse() {
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public LocalDateTime getAgreementDateFrom() {
        return agreementDateFrom;
    }

    public void setAgreementDateFrom(LocalDateTime agreementDateFrom) {
        this.agreementDateFrom = agreementDateFrom;
    }

    public LocalDateTime getAgreementDateTo() {
        return agreementDateTo;
    }

    public void setAgreementDateTo(LocalDateTime agreementDateTo) {
        this.agreementDateTo = agreementDateTo;
    }
}
