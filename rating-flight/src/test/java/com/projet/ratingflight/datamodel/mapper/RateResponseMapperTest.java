package com.projet.ratingflight.datamodel.mapper;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RateResponseMapperTest {

    private final RateResponseMapper mapper = Mappers.getMapper(RateResponseMapper.class);

    @Test
    void toRateResponseDTO_shouldMapAllFields() {
        // Given
        RateEntity rateEntity = RateEntity.builder()
                .id(1L)
                .build();

        RateResponseEntity entity = new RateResponseEntity();
        entity.setId(1L);
        entity.setResponse("Merci pour votre retour");
        entity.setResponseAt(LocalDateTime.now());
        entity.setRate(rateEntity);

        // When
        RateResponseDTO dto = mapper.toRateResponseDTO(entity);

        // Then
        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getResponse(), dto.getResponse());
        assertEquals(entity.getResponseAt(), dto.getResponseAt());
    }

    @Test
    void toRateResponseEntity_shouldMapAllFields() {
        // Given
        RateResponseDTO dto = RateResponseDTO.builder()
                .id(1L)
                .response("Merci pour votre retour")
                .responseAt(LocalDateTime.now())
                .build();

        RateEntity rateEntity = RateEntity.builder()
                .id(1L)
                .build();

        // When
        RateResponseEntity entity = mapper.toRateResponseEntity(dto);

        // Then
        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getResponse(), entity.getResponse());
        assertEquals(dto.getResponseAt(), entity.getResponseAt());
    }

    @Test
    void mapping_shouldHandleNullValues() {
        // Given
        RateResponseEntity entity = new RateResponseEntity();

        // When
        RateResponseDTO dto = mapper.toRateResponseDTO(entity);

        // Then
        assertNotNull(dto);
        assertNull(dto.getId());
        assertNull(dto.getResponse());
        assertNull(dto.getResponseAt());
    }
}
