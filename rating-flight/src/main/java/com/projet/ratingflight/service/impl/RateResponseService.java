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

/**
 * Service gérant les réponses aux évaluations de vols.
 * Permet de créer et consulter les réponses données aux évaluations des utilisateurs.
 */
@Service
@RequiredArgsConstructor
public class RateResponseService implements IRateResponseService {

    private final RateResponseRepository rateResponseRepository;
    private final RateRepository rateRepository;
    private final RateResponseMapper rateResponseMapper;

    /**
     * Récupère toutes les réponses aux évaluations enregistrées dans le système.
     *
     * @return Une liste de toutes les réponses sous forme de DTOs
     */
    @Override
    public List<RateResponseDTO> getAllRates() {
        return rateResponseRepository.findAll().stream()
                .map(rateResponseMapper::toRateResponseDTO)
                .toList();
    }

    /**
     * Crée une nouvelle réponse pour une évaluation donnée.
     * La réponse doit être non vide et associée à une évaluation existante.
     *
     * @param rateId L'identifiant de l'évaluation à laquelle la réponse est associée
     * @param response Le contenu de la réponse
     * @return La réponse créée sous forme de DTO
     */
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
