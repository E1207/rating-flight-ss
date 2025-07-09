package com.projet.ratingflight.controller;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import com.projet.ratingflight.repository.RateRepository;
import com.projet.ratingflight.request.RateResponserequest;
import com.projet.ratingflight.service.IRateResponseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rates")
public class RateResponseController {

    private final IRateResponseService rateResponseService;

    @GetMapping("/responses")
    public List<RateResponseDTO> getAllRates() {
        return rateResponseService.getAllRates();
    }

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
