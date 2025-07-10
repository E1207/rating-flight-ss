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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateService implements IRateService {

    private final RateRepository rateRepository;

    private final RateMapper rateMapper;

    /**
     * Récupère toutes les évaluations enregistrées dans le système.
     *
     * @return Une liste de toutes les évaluations sous forme de DTOs
     */
    @Override
    public List<RateDTO> getAllRates() {
        return rateRepository.findAll().stream()
                .map(rateMapper::toRateDTO)
                .toList();
    }

    /**
     * Enregistre une nouvelle évaluation ou met à jour une évaluation existante.
     * Si le status n'est pas défini, il sera initialisé à PENDING.
     * Si la date de soumission n'est pas définie, elle sera initialisée à la date courante.
     *
     * @param rateDTO L'évaluation à sauvegarder
     * @return L'évaluation sauvegardée avec son ID et ses champs mis à jour
     */
    @Override
    public RateDTO saveRate(RateDTO rateDTO) {
        if (rateDTO.getStatus() == null) {
            rateDTO.setStatus(RateStatus.PENDING);
        }
        if (rateDTO.getSubmittedAt() == null) {
            rateDTO.setSubmittedAt(LocalDateTime.now().withNano(0));
        }
        return rateMapper.toRateDTO(rateRepository.save(rateMapper.toRateEntity(rateDTO)));
    }

    /**
     * Met à jour le statut d'une évaluation existante.
     *
     * @param id L'identifiant de l'évaluation à mettre à jour
     * @param status Le nouveau statut à appliquer
     * @return L'évaluation mise à jour
     * @throws EntityNotFoundException si l'évaluation n'est pas trouvée
     */
    @Override
    public RateDTO updateRateStatus(Long id, RateStatus status) {
        RateEntity entity = rateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found"));

        entity.setStatus(status);
        RateEntity savedEntity = rateRepository.save(entity);

        return rateMapper.toRateDTO(savedEntity);
    }

    /**
     * Récupère toutes les évaluations ayant un statut spécifique.
     *
     * @param status Le statut des évaluations à récupérer
     * @return Une liste des évaluations correspondant au statut demandé
     */
    @Override
    public List<RateDTO> getRatesByStatus(RateStatus status) {
        return rateRepository.findByStatus(status).stream()
                .map(rateMapper::toRateDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupère toutes les évaluations publiées (ayant le statut PUBLISHED).
     *
     * @return Une liste des évaluations publiées
     */
    @Override
    public List<RateDTO> getPublishedRates() {
        return rateRepository.findByStatus(RateStatus.PUBLISHED).stream()
                .map(rateMapper::toRateDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupère une évaluation par son identifiant.
     *
     * @param id L'identifiant de l'évaluation à récupérer
     * @return L'évaluation correspondante
     * @throws EntityNotFoundException si l'évaluation n'est pas trouvée
     */
    @Override
    public RateDTO getRateById(Long id) {
        RateEntity entity = rateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with id: " + id));
        return rateMapper.toRateDTO(entity);
    }
}
