package com.projet.ratingflight.datamodel.mapper;

import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-10T01:43:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class RateResponseMapperImpl implements RateResponseMapper {

    @Override
    public RateResponseDTO toRateResponseDTO(RateResponseEntity rateEntity) {
        if ( rateEntity == null ) {
            return null;
        }

        Long id = null;
        String response = null;
        LocalDateTime responseAt = null;

        id = rateEntity.getId();
        response = rateEntity.getResponse();
        responseAt = rateEntity.getResponseAt();

        RateResponseDTO rateResponseDTO = new RateResponseDTO( id, response, responseAt );

        return rateResponseDTO;
    }
}
