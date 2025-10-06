package com.asso.lesbonsjouets.gestionDesVentes.domain.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record JourDeVenteDto(
        UUID id,
        LocalDate jour,
        String description,
        Integer nombreDeVentes,
        Double totalVentes,
        List<VenteDto> ventes
) {
}
