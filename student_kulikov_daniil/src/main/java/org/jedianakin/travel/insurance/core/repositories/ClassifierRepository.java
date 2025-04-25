package org.jedianakin.travel.insurance.core.repositories;

import org.jedianakin.travel.insurance.core.domain.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {

    Optional<Classifier> findByTitle(String title);

}
