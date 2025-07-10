package com.projet.ratingflight.controller;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.request.RateResponserequest;
import com.projet.ratingflight.service.IRateResponseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST gérant les réponses aux évaluations de vols.
 * Fournit les endpoints pour la création et la consultation des réponses aux évaluations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rates")
public class RateResponseController {

    private final IRateResponseService rateResponseService;

    /**
     * Récupère toutes les réponses aux évaluations
     * @return Liste de toutes les réponses
     */
    @GetMapping("/responses")
    public List<RateResponseDTO> getAllRates() {
        return rateResponseService.getAllRates();
    }

    /**
     * Crée une nouvelle réponse pour une évaluation donnée
     * @param rateId Identifiant de l'évaluation
     * @param request Contenu de la réponse
     * @return La réponse créée
     */
    @PostMapping("/{rateId}/responses")
    public ResponseEntity<RateResponseDTO> createResponse(
            @PathVariable Long rateId,
            @RequestBody RateResponserequest request) {
        try {
            RateResponseDTO createdResponse = rateResponseService.createResponse(rateId, request.getResponse());
            return ResponseEntity.ok(createdResponse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
