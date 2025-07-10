package com.projet.ratingflight.service.impl;

import com.projet.ratingflight.datamodel.dto.RateDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.enums.RateStatus;
import com.projet.ratingflight.datamodel.mapper.RateMapper;
import com.projet.ratingflight.repository.RateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RateServiceTest {

    @Mock
    private RateRepository rateRepository;

    @Mock
    private RateMapper rateMapper;

    @InjectMocks
    private RateService rateService;

    private RateEntity testRateEntity;
    private RateDTO testRateDTO;

    @BeforeEach
    void setUp() {
        testRateEntity = RateEntity.builder()
                .id(1L)
                .rating(5)
                .comment("Excellent service")
                .flightNumber("AF123")
                .company("Air France")
                .flightDate(LocalDate.now())
                .submittedAt(LocalDateTime.now())
                .status(RateStatus.PENDING)
                .build();

        testRateDTO = RateDTO.builder()
                .id(1L)
                .rating(5)
                .comment("Excellent service")
                .flightNumber("AF123")
                .company("Air France")
                .flightDate(LocalDate.now())
                .submittedAt(LocalDateTime.now())
                .status(RateStatus.PENDING)
                .build();
    }

    @Test
    void getAllRates_ShouldReturnAllRates() {
        // Given
        List<RateEntity> entities = Arrays.asList(testRateEntity);
        when(rateRepository.findAll()).thenReturn(entities);
        when(rateMapper.toRateDTO(any(RateEntity.class))).thenReturn(testRateDTO);

        // When
        List<RateDTO> result = rateService.getAllRates();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testRateDTO, result.get(0));
        verify(rateRepository).findAll();
        verify(rateMapper).toRateDTO(testRateEntity);
    }

    @Test
    void saveRate_ShouldSaveAndReturnRate() {
        // Given
        when(rateMapper.toRateEntity(any(RateDTO.class))).thenReturn(testRateEntity);
        when(rateRepository.save(any(RateEntity.class))).thenReturn(testRateEntity);
        when(rateMapper.toRateDTO(any(RateEntity.class))).thenReturn(testRateDTO);

        // When
        RateDTO result = rateService.saveRate(testRateDTO);

        // Then
        assertNotNull(result);
        assertEquals(testRateDTO, result);
        verify(rateMapper).toRateEntity(testRateDTO);
        verify(rateRepository).save(testRateEntity);
        verify(rateMapper).toRateDTO(testRateEntity);
    }

    @Test
    void updateRateStatus_WithValidId_ShouldUpdateStatus() {
        // Given
        RateEntity updatedEntity = testRateEntity;
        updatedEntity.setStatus(RateStatus.PUBLISHED);

        when(rateRepository.findById(1L)).thenReturn(Optional.of(testRateEntity));
        when(rateRepository.save(any(RateEntity.class))).thenReturn(updatedEntity);
        when(rateMapper.toRateDTO(any(RateEntity.class))).thenReturn(testRateDTO);

        // When
        RateDTO result = rateService.updateRateStatus(1L, RateStatus.PUBLISHED);

        // Then
        assertNotNull(result);
        assertEquals(RateStatus.PUBLISHED, result.getStatus());
        verify(rateRepository).findById(1L);
        verify(rateRepository).save(testRateEntity);
    }

    @Test
    void updateRateStatus_WithInvalidId_ShouldThrowException() {
        // Given
        when(rateRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () ->
            rateService.updateRateStatus(999L, RateStatus.PUBLISHED)
        );
        verify(rateRepository).findById(999L);
        verify(rateRepository, never()).save(any());
    }

    @Test
    void getRatesByStatus_ShouldReturnFilteredRates() {
        // Given
        List<RateEntity> entities = Arrays.asList(testRateEntity);
        when(rateRepository.findByStatus(RateStatus.PENDING)).thenReturn(entities);
        when(rateMapper.toRateDTO(any(RateEntity.class))).thenReturn(testRateDTO);

        // When
        List<RateDTO> result = rateService.getRatesByStatus(RateStatus.PENDING);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testRateDTO, result.get(0));
        verify(rateRepository).findByStatus(RateStatus.PENDING);
    }

    @Test
    void getPublishedRates_ShouldReturnPublishedRates() {
        // Given
        List<RateEntity> entities = Arrays.asList(testRateEntity);
        when(rateRepository.findByStatus(RateStatus.PUBLISHED)).thenReturn(entities);
        when(rateMapper.toRateDTO(any(RateEntity.class))).thenReturn(testRateDTO);

        // When
        List<RateDTO> result = rateService.getPublishedRates();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testRateDTO, result.get(0));
        verify(rateRepository).findByStatus(RateStatus.PUBLISHED);
    }

    @Test
    void getRateById_WithValidId_ShouldReturnRate() {
        // Given
        when(rateRepository.findById(1L)).thenReturn(Optional.of(testRateEntity));
        when(rateMapper.toRateDTO(testRateEntity)).thenReturn(testRateDTO);

        // When
        RateDTO result = rateService.getRateById(1L);

        // Then
        assertNotNull(result);
        assertEquals(testRateDTO, result);
        verify(rateRepository).findById(1L);
        verify(rateMapper).toRateDTO(testRateEntity);
    }

    @Test
    void getRateById_WithInvalidId_ShouldThrowException() {
        // Given
        when(rateRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () ->
            rateService.getRateById(999L)
        );
        verify(rateRepository).findById(999L);
        verify(rateMapper, never()).toRateDTO(any());
    }
}
