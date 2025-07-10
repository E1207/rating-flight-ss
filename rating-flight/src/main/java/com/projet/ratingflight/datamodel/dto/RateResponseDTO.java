package com.projet.ratingflight.datamodel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RateResponseDTO {

    private Long id;

    private String response;

    private LocalDateTime responseAt;
}

