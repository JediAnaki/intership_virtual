package org.jedianakin.travel.insurance.core.messagebroker.proposal;

import org.jedianakin.travel.insurance.core.api.dto.AgreementDTO;

public interface ProposalGeneratorQueueSender {

    void send(AgreementDTO agreement);

}
