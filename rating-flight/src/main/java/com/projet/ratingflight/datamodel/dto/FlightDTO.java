package com.projet.ratingflight.datamodel.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FlightDTO {
    // Attributs
    private Long id;

    private String flightNumber;

    private String company;

    private LocalDate flightDate;

    private List<RateDTO> rates;

}

