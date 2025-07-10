package com.projet.ratingflight.datamodel.mapper;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import org.mapstruct.Mapper;

/**
 * Mapper pour convertir entre les entités RateResponse et leurs DTOs.
 * Utilise MapStruct pour gérer automatiquement les conversions entre les différents types.
 */
@Mapper(componentModel = "spring")
public interface RateResponseMapper {

    RateResponseDTO toRateResponseDTO(RateResponseEntity rateEntity);
    RateResponseEntity toRateResponseEntity(RateResponseDTO rateResponseDTO);
}


