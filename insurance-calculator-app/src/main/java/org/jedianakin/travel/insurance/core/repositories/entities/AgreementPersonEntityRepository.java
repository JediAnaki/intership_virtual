package org.jedianakin.travel.insurance.core.repositories.entities;

import org.jedianakin.travel.insurance.core.domain.entities.AgreementEntity;
import org.jedianakin.travel.insurance.core.domain.entities.AgreementPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgreementPersonEntityRepository
        extends JpaRepository<AgreementPersonEntity, Long> {

    List<AgreementPersonEntity> findByAgreement(AgreementEntity agreement);

}
