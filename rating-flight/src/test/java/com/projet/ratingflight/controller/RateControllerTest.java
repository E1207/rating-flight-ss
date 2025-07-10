package com.projet.ratingflight.controller;

import com.projet.ratingflight.datamodel.dto.RateDTO;
import com.projet.ratingflight.datamodel.enums.RateStatus;
import com.projet.ratingflight.service.IRateService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour le contrôleur RateController
 */
@ExtendWith(MockitoExtension.class)
class RateControllerTest {

    @Mock
    private IRateService rateService;

    @InjectMocks
    private RateController rateController;

    private RateDTO sampleRateDTO;

    @BeforeEach
    void setUp() {
        sampleRateDTO = RateDTO.builder()
                .id(1L)
                .rating(5)
                .comment("Excellent vol !")
                .flightNumber("AF123")
                .company("Air France")
                .flightDate(LocalDate.now())
                .submittedAt(LocalDateTime.now())
                .status(RateStatus.PENDING)
                .build();
    }

    @Test
    void getAllRates_ShouldReturnListOfRates() {
        // Given
        List<RateDTO> expectedRates = Arrays.asList(sampleRateDTO);
        when(rateService.getAllRates()).thenReturn(expectedRates);

        // When
        List<RateDTO> actualRates = rateController.getAllRates();

        // Then
        assertEquals(expectedRates, actualRates);
        verify(rateService).getAllRates();
    }

    @Test
    void getRatesByStatus_ShouldReturnFilteredRates() {
        // Given
        List<RateDTO> expectedRates = Arrays.asList(sampleRateDTO);
        when(rateService.getRatesByStatus(RateStatus.PENDING)).thenReturn(expectedRates);

        // When
        List<RateDTO> actualRates = rateController.getRatesByStatus(RateStatus.PENDING);

        // Then
        assertEquals(expectedRates, actualRates);
        verify(rateService).getRatesByStatus(RateStatus.PENDING);
    }

    @Test
    void updateStatus_WithValidId_ShouldReturnUpdatedRate() {
        // Given
        when(rateService.updateRateStatus(1L, RateStatus.PUBLISHED))
                .thenReturn(sampleRateDTO);

        // When
        ResponseEntity<RateDTO> response = rateController.updateStatus(1L, RateStatus.PUBLISHED);

        // Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(sampleRateDTO, response.getBody());
    }

    @Test
    void updateStatus_WithInvalidId_ShouldReturnNotFound() {
        // Given
        when(rateService.updateRateStatus(999L, RateStatus.PUBLISHED))
                .thenThrow(EntityNotFoundException.class);

        // When
        ResponseEntity<RateDTO> response = rateController.updateStatus(999L, RateStatus.PUBLISHED);

        // Then
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void getPublishedRates_ShouldReturnPublishedRates() {
        // Given
        List<RateDTO> expectedRates = Arrays.asList(sampleRateDTO);
        when(rateService.getPublishedRates()).thenReturn(expectedRates);

        // When
        List<RateDTO> actualRates = rateController.getPublishedRates();

        // Then
        assertEquals(expectedRates, actualRates);
        verify(rateService).getPublishedRates();
    }

    @Test
    void createRate_ShouldReturnCreatedRate() {
        // Given
        when(rateService.saveRate(sampleRateDTO)).thenReturn(sampleRateDTO);

        // When
        RateDTO createdRate = rateController.createRate(sampleRateDTO);

        // Then
        assertEquals(sampleRateDTO, createdRate);
        verify(rateService).saveRate(sampleRateDTO);
    }

    @Test
    void getRateById_WithValidId_ShouldReturnRate() {
        // Given
        when(rateService.getRateById(1L)).thenReturn(sampleRateDTO);

        // When
        ResponseEntity<RateDTO> response = rateController.getRateById(1L);

        // Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(sampleRateDTO, response.getBody());
    }

    @Test
    void getRateById_WithInvalidId_ShouldReturnNotFound() {
        // Given
        when(rateService.getRateById(999L)).thenThrow(EntityNotFoundException.class);

        // When
        ResponseEntity<RateDTO> response = rateController.getRateById(999L);

        // Then
        assertTrue(response.getStatusCode().is4xxClientError());
    }
}
