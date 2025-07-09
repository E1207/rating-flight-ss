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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "flight_number", length = 100)
    private String flightNumber;

    @Column(name = "company", length = 100)
    private String company;

    @Column(name = "flight_date")
    private LocalDate flightDate;

    @Column(name = "submitted_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Builder.Default
    private LocalDateTime submittedAt = LocalDateTime.now().withNano(0);

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private RateStatus status = RateStatus.PENDING;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_id", referencedColumnName = "id")
    @OrderBy("responseAt DESC")
    private List<RateResponseEntity> rateResponse;
}
