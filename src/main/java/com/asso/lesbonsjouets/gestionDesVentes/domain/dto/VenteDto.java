package com.asso.lesbonsjouets.gestionDesVentes.domain.dto;

import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.TypeDeVente;

import java.util.UUID;

public record VenteDto(
        UUID id,
        TypeDeVente typeDeVente,
        String description,
        Double totalVente
) {
}
