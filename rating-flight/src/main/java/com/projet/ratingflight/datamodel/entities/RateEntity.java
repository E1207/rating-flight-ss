package com.projet.ratingflight.datamodel.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projet.ratingflight.datamodel.enums.RateStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entité représentant une évaluation de vol.
 * Cette classe stocke les informations relatives aux évaluations faites par les utilisateurs pour un vol spécifique.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rate")
public class RateEntity {

    /**
     * Identifiant unique de l'évaluation
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Note attribuée au vol (obligatoire)
     */
    @Column(nullable = false, name = "rating")
    private int rating;

    /**
     * Commentaire associé à l'évaluation (optionnel, max 500 caractères)
     */
    @Column(length = 500, name = "comment")
    private String comment;

    /**
     * Numéro du vol évalué
     */
    @Column(name = "flight_number", length = 100)
    private String flightNumber;

    /**
     * Nom de la compagnie aérienne
     */
    @Column(name = "company", length = 100)
    private String company;

    /**
     * Date du vol
     */
    @Column(name = "flight_date")
    private LocalDate flightDate;

    /**
     * Date et heure de soumission de l'évaluation
     * Automatiquement initialisée à la date/heure courante
     */
    @Column(name = "submitted_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Builder.Default
    private LocalDateTime submittedAt = LocalDateTime.now().withNano(0);

    /**
     * Statut de l'évaluation (PENDING par défaut)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private RateStatus status = RateStatus.PENDING;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_id", referencedColumnName = "id")
    @OrderBy("responseAt DESC")
    private List<RateResponseEntity> rateResponse;
}
