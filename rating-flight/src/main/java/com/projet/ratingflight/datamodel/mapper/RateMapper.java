package com.projet.ratingflight.datamodel.mapper;

import com.projet.ratingflight.datamodel.dto.RateDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateMapper {

    RateDTO toRateDTO(RateEntity rateEntity);
    RateEntity toRateEntity(RateDTO rateDTO);
}
