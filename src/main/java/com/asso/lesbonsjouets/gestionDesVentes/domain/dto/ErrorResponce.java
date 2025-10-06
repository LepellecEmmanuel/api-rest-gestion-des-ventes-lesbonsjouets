package com.asso.lesbonsjouets.gestionDesVentes.domain.dto;

public record ErrorResponce(
        int status,
        String message,
        String details
) {
}
