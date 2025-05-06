package org.jedianakin.doc.generator.core.messagebroker.proposalack;

import org.jedianakin.doc.generator.core.api.dto.AgreementDTO;

public interface ProposalGenerationAckQueueSender {

    void send(AgreementDTO agreement, String proposalFilePath);

}
