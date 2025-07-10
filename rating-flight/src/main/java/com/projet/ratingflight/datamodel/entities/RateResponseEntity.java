package com.projet.ratingflight.datamodel.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entité représentant une réponse à une évaluation de vol.
 * Cette classe permet de stocker les réponses données aux évaluations des utilisateurs.
 */
@Data
@Entity
@Table(name = "rate_response")
public class RateResponseEntity {

    /**
     * Identifiant unique de la réponse
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Contenu de la réponse (limité à 500 caractères)
     */
    @Column(nullable = false, name = "response", length = 500)
    private String response;

    /**
     * Date et heure de la réponse
     */
    @Column(name = "response_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime responseAt;

    /**
     * Référence vers l'évaluation associée à cette réponse
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id", nullable = false)
    private RateEntity rate;

}
