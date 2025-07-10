package com.projet.ratingflight.repository;

import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateResponseRepository extends JpaRepository<RateResponseEntity, Long> {
}
