package com.projet.ratingflight.datamodel.mapper;

import com.projet.ratingflight.datamodel.dto.RateDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.enums.RateStatus;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RateMapperTest {

    private final RateMapper mapper = Mappers.getMapper(RateMapper.class);

    @Test
    void toRateDTO_shouldMapAllFields() {
        // Given
        RateEntity entity = RateEntity.builder()
                .id(1L)
                .rating(5)
                .comment("Excellent service")
                .flightNumber("AF123")
                .company("Air France")
                .flightDate(LocalDate.now())
                .submittedAt(LocalDateTime.now())
                .status(RateStatus.PENDING)
                .build();

        // When
        RateDTO dto = mapper.toRateDTO(entity);

        // Then
        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getRating(), dto.getRating());
        assertEquals(entity.getComment(), dto.getComment());
        assertEquals(entity.getFlightNumber(), dto.getFlightNumber());
        assertEquals(entity.getCompany(), dto.getCompany());
        assertEquals(entity.getFlightDate(), dto.getFlightDate());
        assertEquals(entity.getSubmittedAt(), dto.getSubmittedAt());
        assertEquals(entity.getStatus(), dto.getStatus());
    }

    @Test
    void toRateEntity_shouldMapAllFields() {
        // Given
        RateDTO dto = RateDTO.builder()
                .id(1L)
                .rating(5)
                .comment("Excellent service")
                .flightNumber("AF123")
                .company("Air France")
                .flightDate(LocalDate.now())
                .submittedAt(LocalDateTime.now())
                .status(RateStatus.PENDING)
                .build();

        // When
        RateEntity entity = mapper.toRateEntity(dto);

        // Then
        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getRating(), entity.getRating());
        assertEquals(dto.getComment(), entity.getComment());
        assertEquals(dto.getFlightNumber(), entity.getFlightNumber());
        assertEquals(dto.getCompany(), entity.getCompany());
        assertEquals(dto.getFlightDate(), entity.getFlightDate());
        assertEquals(dto.getSubmittedAt(), entity.getSubmittedAt());
        assertEquals(dto.getStatus(), entity.getStatus());
    }

    @Test
    void mapping_shouldHandleNullValues() {
        // Given
        RateEntity entity = new RateEntity();

        // When
        RateDTO dto = mapper.toRateDTO(entity);

        // Then
        assertNotNull(dto);
        assertNull(dto.getId());
        assertNull(dto.getComment());
        assertNull(dto.getFlightNumber());
        assertNull(dto.getCompany());
        assertNull(dto.getFlightDate());
        assertEquals(0, dto.getRating());
    }
}
