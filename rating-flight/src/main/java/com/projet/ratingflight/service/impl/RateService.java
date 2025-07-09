package com.projet.ratingflight.service.impl;

import com.projet.ratingflight.datamodel.dto.RateDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.enums.RateStatus;
import com.projet.ratingflight.datamodel.mapper.RateMapper;
import com.projet.ratingflight.repository.RateRepository;
import com.projet.ratingflight.service.IRateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateService implements IRateService {

    private final RateRepository rateRepository;

    private final RateMapper rateMapper;

    @Override
    public List<RateDTO> getAllRates() {
        return rateRepository.findAll().stream()
                .map(rateMapper::toRateDTO)
                .toList();
    }

    @Override
    public RateDTO saveRate(RateDTO rateDTO) {
        return rateMapper.toRateDTO(rateRepository.save(rateMapper.toRateEntity(rateDTO)));
    }

    public RateDTO updateRateStatus(Long id, RateStatus status) {
        RateEntity entity = rateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found"));

        entity.setStatus(status);
        RateEntity savedEntity = rateRepository.save(entity);

        // Mapping manuel temporaire pour tester
        RateDTO dto = rateMapper.toRateDTO(savedEntity);
        dto.setStatus(savedEntity.getStatus()); // Forcer le mapping du status

        return dto;
    }

    @Override
    public List<RateDTO> getRatesByStatus(RateStatus status) {
        return rateRepository.findByStatus(status).stream()
                .map(rateMapper::toRateDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RateDTO> getPublishedRates() {
        return rateRepository.findByStatus(RateStatus.PUBLISHED).stream()
                .map(rateMapper::toRateDTO)
                .collect(Collectors.toList());
    }
    @Override
    public RateDTO getRateById(Long id) {
        RateEntity entity = rateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with id: " + id));
        return rateMapper.toRateDTO(entity);
    }
}
