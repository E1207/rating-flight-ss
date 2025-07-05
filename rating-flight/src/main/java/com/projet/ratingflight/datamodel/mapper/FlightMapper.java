package com.projet.ratingflight.datamodel.mapper;

import com.projet.ratingflight.datamodel.dto.FlightDTO;
import com.projet.ratingflight.datamodel.entities.FlightEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightDTO toFlightDTO(FlightEntity flightEntity);
    FlightEntity toFlightEntity(FlightDTO flightDTO);

}

