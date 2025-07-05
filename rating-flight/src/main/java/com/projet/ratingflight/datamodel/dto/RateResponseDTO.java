package com.projet.ratingflight.datamodel.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RateResponseDTO {

    private Long id;

    private String response;

    private LocalDateTime responseAt;
}

