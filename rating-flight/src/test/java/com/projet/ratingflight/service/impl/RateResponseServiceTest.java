package com.projet.ratingflight.service.impl;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import com.projet.ratingflight.datamodel.mapper.RateResponseMapper;
import com.projet.ratingflight.repository.RateRepository;
import com.projet.ratingflight.repository.RateResponseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RateResponseServiceTest {

    @Mock
    private RateResponseRepository rateResponseRepository;

    @Mock
    private RateRepository rateRepository;

    @Mock
    private RateResponseMapper rateResponseMapper;

    @InjectMocks
    private RateResponseService rateResponseService;

    private RateEntity testRateEntity;
    private RateResponseEntity testResponseEntity;
    private RateResponseDTO testResponseDTO;

    @BeforeEach
    void setUp() {
        testRateEntity = RateEntity.builder()
                .id(1L)
                .build();

        testResponseEntity = new RateResponseEntity();
        testResponseEntity.setId(1L);
        testResponseEntity.setResponse("Merci pour votre retour");
        testResponseEntity.setResponseAt(LocalDateTime.now());
        testResponseEntity.setRate(testRateEntity);

        testResponseDTO = RateResponseDTO.builder()
                .id(1L)
                .response("Merci pour votre retour")
                .responseAt(LocalDateTime.now())
                .build();
    }

    @Test
    void getAllRates_ShouldReturnAllResponses() {
        // Given
        List<RateResponseEntity> entities = Arrays.asList(testResponseEntity);
        when(rateResponseRepository.findAll()).thenReturn(entities);
        when(rateResponseMapper.toRateResponseDTO(any(RateResponseEntity.class))).thenReturn(testResponseDTO);

        // When
        List<RateResponseDTO> result = rateResponseService.getAllRates();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testResponseDTO, result.get(0));
        verify(rateResponseRepository).findAll();
        verify(rateResponseMapper).toRateResponseDTO(testResponseEntity);
    }

    @Test
    void createResponse_WithValidRateId_ShouldCreateResponse() {
        // Given
        String responseText = "Merci pour votre retour";
        when(rateRepository.findById(1L)).thenReturn(Optional.of(testRateEntity));
        when(rateResponseRepository.save(any(RateResponseEntity.class))).thenReturn(testResponseEntity);
        when(rateResponseMapper.toRateResponseDTO(any(RateResponseEntity.class))).thenReturn(testResponseDTO);

        // When
        RateResponseDTO result = rateResponseService.createResponse(1L, responseText);

        // Then
        assertNotNull(result);
        assertEquals(testResponseDTO, result);
        verify(rateRepository).findById(1L);
        verify(rateResponseRepository).save(any(RateResponseEntity.class));
        verify(rateResponseMapper).toRateResponseDTO(any(RateResponseEntity.class));
    }

    @Test
    void createResponse_WithInvalidRateId_ShouldThrowException() {
        // Given
        String responseText = "Merci pour votre retour";
        when(rateRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () ->
            rateResponseService.createResponse(999L, responseText)
        );
        verify(rateRepository).findById(999L);
        verify(rateResponseRepository, never()).save(any());
        verify(rateResponseMapper, never()).toRateResponseDTO(any());
    }

    @Test
    void createResponse_WithNullResponse_ShouldThrowException() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () ->
            rateResponseService.createResponse(1L, null)
        );
        verify(rateRepository, never()).findById(any());
        verify(rateResponseRepository, never()).save(any());
    }

    @Test
    void createResponse_WithEmptyResponse_ShouldThrowException() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () ->
            rateResponseService.createResponse(1L, "")
        );
        verify(rateRepository, never()).findById(any());
        verify(rateResponseRepository, never()).save(any());
    }
}
