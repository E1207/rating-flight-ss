package com.projet.ratingflight.datamodel.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RateDTO {

    private Long id;

    private int rating;

    private String comment;

    private LocalDateTime submittedAt;

    private FlightDTO flight;

    private RateResponseDTO rateResponse;

}

