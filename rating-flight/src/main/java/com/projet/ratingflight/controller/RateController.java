package com.projet.ratingflight.controller;

import com.projet.ratingflight.datamodel.dto.RateDTO;
import com.projet.ratingflight.datamodel.enums.RateStatus;
import com.projet.ratingflight.service.IRateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rates")
public class RateController {

    private final IRateService rateService;


     @GetMapping
     public List<RateDTO> getAllRates() {
         return rateService.getAllRates();
     }

    @GetMapping("/status/{status}")
    public List<RateDTO> getRatesByStatus(@PathVariable RateStatus status) {
        return rateService.getRatesByStatus(status);
    }

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

    @GetMapping("/published")
    public List<RateDTO> getPublishedRates() {
        return rateService.getPublishedRates();
    }

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
