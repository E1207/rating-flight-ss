package com.projet.ratingflight.service.impl;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import com.projet.ratingflight.datamodel.mapper.RateResponseMapper;
import com.projet.ratingflight.repository.RateRepository;
import com.projet.ratingflight.repository.RateResponseRepository;
import com.projet.ratingflight.service.IRateResponseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RateResponseService implements IRateResponseService {

    private final RateResponseRepository rateResponseRepository;
    private final RateRepository rateRepository;
    private final RateResponseMapper rateResponseMapper;

    @Override
    public List<RateResponseDTO> getAllRates() {
        return rateResponseRepository.findAll().stream()
                .map(rateResponseMapper::toRateResponseDTO)
                .toList();
    }

    @Override
    public RateResponseDTO createResponse(Long rateId, String response) {
        RateEntity rate = rateRepository.findById(rateId)
                .orElseThrow(() -> new RuntimeException("Avis non trouvé avec l'ID : " + rateId));

        RateResponseEntity rateResponse = new RateResponseEntity();
        rateResponse.setRate(rate);
        rateResponse.setResponse(response);
        rateResponse.setResponseAt(LocalDateTime.now());

        RateResponseEntity savedResponse = rateResponseRepository.save(rateResponse);
        return rateResponseMapper.toRateResponseDTO(savedResponse);
    }
}
