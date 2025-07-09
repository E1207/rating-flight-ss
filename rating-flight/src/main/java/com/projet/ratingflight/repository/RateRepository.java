package com.projet.ratingflight.repository;

import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.enums.RateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, Long> {
    List<RateEntity> findByStatus(RateStatus status);
}
