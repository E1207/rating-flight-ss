package com.projet.ratingflight.datamodel.mapper;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-10T14:09:20+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class RateResponseMapperImpl implements RateResponseMapper {

    @Override
    public RateResponseDTO toRateResponseDTO(RateResponseEntity rateEntity) {
        if ( rateEntity == null ) {
            return null;
        }

        RateResponseDTO.RateResponseDTOBuilder rateResponseDTO = RateResponseDTO.builder();

        rateResponseDTO.id( rateEntity.getId() );
        rateResponseDTO.response( rateEntity.getResponse() );
        rateResponseDTO.responseAt( rateEntity.getResponseAt() );

        return rateResponseDTO.build();
    }

    @Override
    public RateResponseEntity toRateResponseEntity(RateResponseDTO rateResponseDTO) {
        if ( rateResponseDTO == null ) {
            return null;
        }

        RateResponseEntity rateResponseEntity = new RateResponseEntity();

        rateResponseEntity.setId( rateResponseDTO.getId() );
        rateResponseEntity.setResponse( rateResponseDTO.getResponse() );
        rateResponseEntity.setResponseAt( rateResponseDTO.getResponseAt() );

        return rateResponseEntity;
    }
}
