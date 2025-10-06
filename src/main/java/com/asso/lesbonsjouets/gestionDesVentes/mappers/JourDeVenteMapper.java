package com.asso.lesbonsjouets.gestionDesVentes.mappers;

import com.asso.lesbonsjouets.gestionDesVentes.domain.dto.JourDeVenteDto;
import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.JourDeVente;

public interface JourDeVenteMapper {
    JourDeVente fromDto(JourDeVenteDto jourDeVenteDto);
    JourDeVenteDto toDto(JourDeVente jourDeVente);
}
