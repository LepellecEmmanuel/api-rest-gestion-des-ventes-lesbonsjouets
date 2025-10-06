package com.asso.lesbonsjouets.gestionDesVentes.mappers.impl;

import com.asso.lesbonsjouets.gestionDesVentes.domain.dto.VenteDto;
import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.Vente;
import com.asso.lesbonsjouets.gestionDesVentes.mappers.VenteMapper;
import org.springframework.stereotype.Component;

@Component
public class VenteMapperImpl implements VenteMapper {
    @Override
    public Vente fromDto(VenteDto venteDto) {
        return new Vente(
                venteDto.id(),
                venteDto.typeDeVente(),
                venteDto.description(),
                venteDto.totalVente(),
                null,
                null,
                null
        );
    }

    @Override
    public VenteDto toDto(Vente vente) {
        return new VenteDto(
                vente.getId(),
                vente.getTypeDeVente(),
                vente.getDescription(),
                vente.getTotalVente()
        );
    }
}
