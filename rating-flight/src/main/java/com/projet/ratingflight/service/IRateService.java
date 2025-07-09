package com.projet.ratingflight.service;

import com.projet.ratingflight.datamodel.dto.RateDTO;
import com.projet.ratingflight.datamodel.enums.RateStatus;

import java.util.List;

public interface IRateService {

    /**
     * Retrieves all rates.
     *
     * @return a list of RateDTO objects representing all rates.
     */
    List<RateDTO> getAllRates();

    RateDTO saveRate(RateDTO rateDTO);


    RateDTO updateRateStatus(Long id, RateStatus status);

    List<RateDTO> getRatesByStatus(RateStatus status);

    List<RateDTO> getPublishedRates();

    RateDTO getRateById(Long id);
}
