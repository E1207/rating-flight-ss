package com.projet.ratingflight.datamodel.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "flight")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "flight_number", length = 100, nullable = false)
    private String flightNumber;

    @Column(name = "company", length = 100, nullable = false)
    private String company;

    @Column(name = "flight_date",nullable = false)
    private LocalDate flightDate;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RateEntity> rates = new ArrayList<>();
}
