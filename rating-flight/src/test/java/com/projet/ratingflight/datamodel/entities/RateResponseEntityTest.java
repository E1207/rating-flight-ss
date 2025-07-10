package com.projet.ratingflight.datamodel.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour l'entité RateResponseEntity
 */
class RateResponseEntityTest {

    @Test
    void testRateResponseEntityBasicOperations() {
        // Given
        RateResponseEntity response = new RateResponseEntity();
        LocalDateTime now = LocalDateTime.now();
        RateEntity rate = new RateEntity();

        // When
        response.setId(1L);
        response.setResponse("Merci pour votre évaluation !");
        response.setResponseAt(now);
        response.setRate(rate);

        // Then
        assertEquals(1L, response.getId());
        assertEquals("Merci pour votre évaluation !", response.getResponse());
        assertEquals(now, response.getResponseAt());
        assertEquals(rate, response.getRate());
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        RateResponseEntity response1 = new RateResponseEntity();
        response1.setId(1L);
        response1.setResponse("Test response");

        RateResponseEntity response2 = new RateResponseEntity();
        response2.setId(1L);
        response2.setResponse("Test response");

        RateResponseEntity response3 = new RateResponseEntity();
        response3.setId(2L);
        response3.setResponse("Test response");

        // Then
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testRateAssociation() {
        // Given
        RateResponseEntity response = new RateResponseEntity();
        RateEntity rate = new RateEntity();
        rate.setId(1L);
        rate.setRating(5);

        // When
        response.setRate(rate);

        // Then
        assertNotNull(response.getRate());
        assertEquals(1L, response.getRate().getId());
        assertEquals(5, response.getRate().getRating());
    }
}
