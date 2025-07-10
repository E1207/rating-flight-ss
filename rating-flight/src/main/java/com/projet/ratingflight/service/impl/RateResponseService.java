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
        if (response == null || response.trim().isEmpty()) {
            throw new IllegalArgumentException("La réponse ne peut pas être vide");
        }

        RateEntity rate = rateRepository.findById(rateId)
                .orElseThrow(() -> new EntityNotFoundException("Avis non trouvé avec l'ID : " + rateId));

        RateResponseEntity rateResponse = new RateResponseEntity();
        rateResponse.setRate(rate);
        rateResponse.setResponse(response.trim());
        rateResponse.setResponseAt(LocalDateTime.now());

        RateResponseEntity savedResponse = rateResponseRepository.save(rateResponse);
        return rateResponseMapper.toRateResponseDTO(savedResponse);
    }
}
