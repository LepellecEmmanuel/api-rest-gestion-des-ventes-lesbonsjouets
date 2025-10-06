package com.asso.lesbonsjouets.gestionDesVentes.mappers;

import com.asso.lesbonsjouets.gestionDesVentes.domain.dto.VenteDto;
import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.Vente;

public interface VenteMapper {
    Vente fromDto(VenteDto venteDto);
    VenteDto toDto(Vente vente);
}
