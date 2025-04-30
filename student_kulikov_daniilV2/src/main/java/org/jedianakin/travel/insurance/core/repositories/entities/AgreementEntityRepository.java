package org.jedianakin.travel.insurance.core.repositories.entities;

import org.jedianakin.travel.insurance.core.domain.entities.AgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgreementEntityRepository extends JpaRepository<AgreementEntity, Long> {

    Optional<AgreementEntity> findByUuid(String uuid);

}
