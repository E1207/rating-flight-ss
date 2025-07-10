package com.projet.ratingflight.datamodel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projet.ratingflight.datamodel.enums.RateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {

    private Long id;

    private int rating;

    private String comment;

    private String flightNumber;

    private String company;

    private LocalDate flightDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submittedAt;

    private RateStatus status;

    private List<RateResponseDTO> rateResponse;

}
