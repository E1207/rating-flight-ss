package com.projet.ratingflight.datamodel.mapper;

import com.projet.ratingflight.datamodel.dto.RateDTO;
import com.projet.ratingflight.datamodel.dto.RateResponseDTO;
import com.projet.ratingflight.datamodel.entities.RateEntity;
import com.projet.ratingflight.datamodel.entities.RateResponseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-10T02:11:01+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class RateMapperImpl implements RateMapper {

    @Override
    public RateDTO toRateDTO(RateEntity rateEntity) {
        if ( rateEntity == null ) {
            return null;
        }

        RateDTO.RateDTOBuilder rateDTO = RateDTO.builder();

        rateDTO.id( rateEntity.getId() );
        rateDTO.rating( rateEntity.getRating() );
        rateDTO.comment( rateEntity.getComment() );
        rateDTO.flightNumber( rateEntity.getFlightNumber() );
        rateDTO.company( rateEntity.getCompany() );
        rateDTO.flightDate( rateEntity.getFlightDate() );
        rateDTO.submittedAt( rateEntity.getSubmittedAt() );
        rateDTO.status( rateEntity.getStatus() );
        rateDTO.rateResponse( rateResponseEntityListToRateResponseDTOList( rateEntity.getRateResponse() ) );

        return rateDTO.build();
    }

    @Override
    public RateEntity toRateEntity(RateDTO rateDTO) {
        if ( rateDTO == null ) {
            return null;
        }

        RateEntity.RateEntityBuilder rateEntity = RateEntity.builder();

        rateEntity.id( rateDTO.getId() );
        rateEntity.rating( rateDTO.getRating() );
        rateEntity.comment( rateDTO.getComment() );
        rateEntity.flightNumber( rateDTO.getFlightNumber() );
        rateEntity.company( rateDTO.getCompany() );
        rateEntity.flightDate( rateDTO.getFlightDate() );
        rateEntity.submittedAt( rateDTO.getSubmittedAt() );
        rateEntity.status( rateDTO.getStatus() );
        rateEntity.rateResponse( rateResponseDTOListToRateResponseEntityList( rateDTO.getRateResponse() ) );

        return rateEntity.build();
    }

    protected RateResponseDTO rateResponseEntityToRateResponseDTO(RateResponseEntity rateResponseEntity) {
        if ( rateResponseEntity == null ) {
            return null;
        }

        RateResponseDTO.RateResponseDTOBuilder rateResponseDTO = RateResponseDTO.builder();

        rateResponseDTO.id( rateResponseEntity.getId() );
        rateResponseDTO.response( rateResponseEntity.getResponse() );
        rateResponseDTO.responseAt( rateResponseEntity.getResponseAt() );

        return rateResponseDTO.build();
    }

    protected List<RateResponseDTO> rateResponseEntityListToRateResponseDTOList(List<RateResponseEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<RateResponseDTO> list1 = new ArrayList<RateResponseDTO>( list.size() );
        for ( RateResponseEntity rateResponseEntity : list ) {
            list1.add( rateResponseEntityToRateResponseDTO( rateResponseEntity ) );
        }

        return list1;
    }

    protected RateResponseEntity rateResponseDTOToRateResponseEntity(RateResponseDTO rateResponseDTO) {
        if ( rateResponseDTO == null ) {
            return null;
        }

        RateResponseEntity rateResponseEntity = new RateResponseEntity();

        rateResponseEntity.setId( rateResponseDTO.getId() );
        rateResponseEntity.setResponse( rateResponseDTO.getResponse() );
        rateResponseEntity.setResponseAt( rateResponseDTO.getResponseAt() );

        return rateResponseEntity;
    }

    protected List<RateResponseEntity> rateResponseDTOListToRateResponseEntityList(List<RateResponseDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<RateResponseEntity> list1 = new ArrayList<RateResponseEntity>( list.size() );
        for ( RateResponseDTO rateResponseDTO : list ) {
            list1.add( rateResponseDTOToRateResponseEntity( rateResponseDTO ) );
        }

        return list1;
    }
}
