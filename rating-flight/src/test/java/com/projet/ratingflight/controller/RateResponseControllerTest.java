package com.projet.ratingflight.controller;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.request.RateResponserequest;
import com.projet.ratingflight.service.IRateResponseService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour le contrôleur RateResponseController
 */
@ExtendWith(MockitoExtension.class)
class RateResponseControllerTest {

    @Mock
    private IRateResponseService rateResponseService;

    @InjectMocks
    private RateResponseController rateResponseController;

    private RateResponseDTO sampleResponseDTO;
    private RateResponserequest sampleRequest;

    @BeforeEach
    void setUp() {
        sampleResponseDTO = RateResponseDTO.builder()
                .id(1L)
                .response("Merci pour votre retour")
                .responseAt(LocalDateTime.now())
                .build();

        sampleRequest = new RateResponserequest();
        sampleRequest.setResponse("Merci pour votre retour");
    }

    @Test
    void getAllRates_ShouldReturnListOfResponses() {
        // Given
        List<RateResponseDTO> expectedResponses = Arrays.asList(sampleResponseDTO);
        when(rateResponseService.getAllRates()).thenReturn(expectedResponses);

        // When
        List<RateResponseDTO> actualResponses = rateResponseController.getAllRates();

        // Then
        assertEquals(expectedResponses, actualResponses);
        verify(rateResponseService).getAllRates();
    }

    @Test
    void createResponse_WithValidData_ShouldReturnCreatedResponse() {
        // Given
        when(rateResponseService.createResponse(1L, sampleRequest.getResponse()))
                .thenReturn(sampleResponseDTO);

        // When
        ResponseEntity<RateResponseDTO> response = rateResponseController.createResponse(1L, sampleRequest);

        // Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(sampleResponseDTO, response.getBody());
    }

    @Test
    void createResponse_WithInvalidRateId_ShouldReturnNotFound() {
        // Given
        when(rateResponseService.createResponse(999L, sampleRequest.getResponse()))
                .thenThrow(EntityNotFoundException.class);

        // When
        ResponseEntity<RateResponseDTO> response = rateResponseController.createResponse(999L, sampleRequest);

        // Then
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void createResponse_WithInvalidData_ShouldReturnBadRequest() {
        // Given
        when(rateResponseService.createResponse(1L, sampleRequest.getResponse()))
                .thenThrow(IllegalArgumentException.class);

        // When
        ResponseEntity<RateResponseDTO> response = rateResponseController.createResponse(1L, sampleRequest);

        // Then
        assertTrue(response.getStatusCode().is4xxClientError());
    }
}
