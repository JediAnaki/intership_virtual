package org.javaguru.travel.insurance.rest;

import java.time.LocalDateTime;

public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;
    private LocalDateTime agreementDateFrom;
    private LocalDateTime agreementDateTo;

    public TravelCalculatePremiumRequest() {
    }

    public TravelCalculatePremiumRequest(String personFirstName, String personLastName, LocalDateTime agreementDateFrom, LocalDateTime agreementDateTo) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;
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
