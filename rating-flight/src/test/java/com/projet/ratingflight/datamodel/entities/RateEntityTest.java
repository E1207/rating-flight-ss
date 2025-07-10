package com.projet.ratingflight.datamodel.entities;

import com.projet.ratingflight.datamodel.enums.RateStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour l'entité RateEntity
 */
class RateEntityTest {

    @Test
    void testRateEntityBuilder() {
        // Given
        LocalDate flightDate = LocalDate.of(2025, 7, 10);
        LocalDateTime submittedAt = LocalDateTime.now().withNano(0);

        // When
        RateEntity rate = RateEntity.builder()
                .rating(5)
                .comment("Excellent vol !")
                .flightNumber("AF123")
                .company("Air France")
                .flightDate(flightDate)
                .submittedAt(submittedAt)
                .status(RateStatus.PENDING)
                .build();

        // Then
        assertNotNull(rate);
        assertEquals(5, rate.getRating());
        assertEquals("Excellent vol !", rate.getComment());
        assertEquals("AF123", rate.getFlightNumber());
        assertEquals("Air France", rate.getCompany());
        assertEquals(flightDate, rate.getFlightDate());
        assertEquals(submittedAt, rate.getSubmittedAt());
        assertEquals(RateStatus.PENDING, rate.getStatus());
    }

    @Test
    void testDefaultValues() {
        // When
        RateEntity rate = new RateEntity();

        // Then
        assertNotNull(rate);
        assertEquals(RateStatus.PENDING, rate.getStatus());
        assertNotNull(rate.getSubmittedAt());
        assertEquals(0, rate.getSubmittedAt().getNano()); // Vérifie que les nanos sont à 0
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        RateEntity rate1 = RateEntity.builder()
                .id(1L)
                .rating(5)
                .flightNumber("AF123")
                .build();

        RateEntity rate2 = RateEntity.builder()
                .id(1L)
                .rating(5)
                .flightNumber("AF123")
                .build();

        RateEntity rate3 = RateEntity.builder()
                .id(2L)
                .rating(5)
                .flightNumber("AF123")
                .build();

        // Then
        assertEquals(rate1, rate2);
        assertNotEquals(rate1, rate3);
        assertEquals(rate1.hashCode(), rate2.hashCode());
        assertNotEquals(rate1.hashCode(), rate3.hashCode());
    }
}
