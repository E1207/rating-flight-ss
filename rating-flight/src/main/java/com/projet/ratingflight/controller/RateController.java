package com.projet.ratingflight.controller;

import com.projet.ratingflight.datamodel.dto.RateDTO;
import com.projet.ratingflight.datamodel.enums.RateStatus;
import com.projet.ratingflight.service.IRateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST gérant les opérations liées aux évaluations de vols.
 * Fournit les endpoints pour la création, la consultation et la mise à jour des évaluations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rates")
public class RateController {

    private final IRateService rateService;

    /**
     * Récupère toutes les évaluations
     * @return Liste de toutes les évaluations
     */
    @GetMapping
    public List<RateDTO> getAllRates() {
        return rateService.getAllRates();
    }

    /**
     * Récupère les évaluations par statut
     * @param status Le statut des évaluations à récupérer
     * @return Liste des évaluations ayant le statut spécifié
     */
    @GetMapping("/status/{status}")
    public List<RateDTO> getRatesByStatus(@PathVariable RateStatus status) {
        return rateService.getRatesByStatus(status);
    }

    /**
     * Met à jour le statut d'une évaluation
     * @param id Identifiant de l'évaluation
     * @param status Nouveau statut à appliquer
     * @return L'évaluation mise à jour
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<RateDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam RateStatus status) {
        try {
            RateDTO updatedRate = rateService.updateRateStatus(id, status);
            return ResponseEntity.ok(updatedRate);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Récupère toutes les évaluations publiées
     * @return Liste des évaluations publiées
     */
    @GetMapping("/published")
    public List<RateDTO> getPublishedRates() {
        return rateService.getPublishedRates();
    }

    /**
     * Crée une nouvelle évaluation
     * @param rateDTO Les données de l'évaluation à créer
     * @return L'évaluation créée
     */
    @PostMapping
    public RateDTO createRate(@RequestBody RateDTO rateDTO) {
        return rateService.saveRate(rateDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateDTO> getRateById(@PathVariable Long id) {
        try {
            RateDTO rate = rateService.getRateById(id);
            return ResponseEntity.ok(rate);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
