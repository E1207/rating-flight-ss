package com.projet.ratingflight.datamodel.mapper;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateResponseMapper {

    RateResponseDTO toRateResponseDTO(RateResponseEntity rateEntity);
    RateResponseEntity toRateResponseEntity(RateResponseDTO rateDTO);
}
