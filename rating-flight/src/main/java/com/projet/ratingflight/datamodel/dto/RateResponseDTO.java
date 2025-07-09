package com.projet.ratingflight.datamodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RateResponseDTO {

    private Long id;

    private String response;

    private LocalDateTime responseAt;
}

