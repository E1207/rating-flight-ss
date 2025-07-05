package com.projet.ratingflight.datamodel.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rate")
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "rating")
    private int rating;

    @Column(length = 500, name = "comment")
    private String comment;

    @Column(name = "submitted_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime SubmittedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private FlightEntity flight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_response_id", referencedColumnName = "id")
    private RateResponseEntity rateResponseEntity;



}
