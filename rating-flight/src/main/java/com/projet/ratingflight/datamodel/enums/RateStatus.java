package com.projet.ratingflight.datamodel.enums;

import lombok.Getter;

@Getter
public enum RateStatus {
    PENDING("En attente"),
    PROCESSED("Traité"),
    PUBLISHED("Publié"),
    REJECTED("Rejeté");

    private final String label;

    RateStatus(String label) {
        this.label = label;
    }

}
