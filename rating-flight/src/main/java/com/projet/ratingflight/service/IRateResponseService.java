package com.projet.ratingflight.service;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;

import java.util.List;

public interface IRateResponseService {

    List<RateResponseDTO> getAllRates();

    RateResponseDTO createResponse(Long rateId, String response);
}
