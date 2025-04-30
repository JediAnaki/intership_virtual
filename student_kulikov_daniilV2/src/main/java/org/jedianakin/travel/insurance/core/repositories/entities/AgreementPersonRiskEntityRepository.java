package org.jedianakin.travel.insurance.core.repositories.entities;

import org.jedianakin.travel.insurance.core.domain.entities.AgreementPersonEntity;
import org.jedianakin.travel.insurance.core.domain.entities.AgreementPersonRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgreementPersonRiskEntityRepository
        extends JpaRepository<AgreementPersonRiskEntity, Long>  {

    List<AgreementPersonRiskEntity> findByAgreementPerson(AgreementPersonEntity agreementPerson);
}
