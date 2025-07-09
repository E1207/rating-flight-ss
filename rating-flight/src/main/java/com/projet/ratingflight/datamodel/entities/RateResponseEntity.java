package com.projet.ratingflight.datamodel.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "rate_response")
public class RateResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "response", length = 500)
    private String response;

    @Column(name = "response_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime responseAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id", nullable = false)
    private RateEntity rate;

}
