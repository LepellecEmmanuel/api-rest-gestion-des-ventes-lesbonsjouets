package com.asso.lesbonsjouets.gestionDesVentes.mappers.impl;

import com.asso.lesbonsjouets.gestionDesVentes.domain.dto.JourDeVenteDto;
import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.JourDeVente;
import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.Vente;
import com.asso.lesbonsjouets.gestionDesVentes.mappers.JourDeVenteMapper;
import com.asso.lesbonsjouets.gestionDesVentes.mappers.VenteMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JourDeVenteMapperImpl implements JourDeVenteMapper {
    private final VenteMapper venteMapper;

    public JourDeVenteMapperImpl(VenteMapper venteMapper) {
        this.venteMapper = venteMapper;
    }

    @Override
    public JourDeVente fromDto(JourDeVenteDto jourDeVenteDto) {
        return new JourDeVente(
                jourDeVenteDto.id(),
                jourDeVenteDto.jour(),
                jourDeVenteDto.description(),
                Optional.ofNullable(jourDeVenteDto.ventes())
                        .map(ventes -> ventes.stream()
                                .map(venteMapper::fromDto)
                                .toList()
                        ).orElse(List.of()),
                null,
                null
        );
    }

    @Override
    public JourDeVenteDto toDto(JourDeVente jourDeVente) {
        return new JourDeVenteDto(
                jourDeVente.getId(),
                jourDeVente.getJour(),
                jourDeVente.getDescription(),
                Optional.ofNullable(jourDeVente.getVentes())
                        .map(List::size)
                        .orElse(0),
                calculateTotalVentes(jourDeVente.getVentes()),
                Optional.ofNullable(jourDeVente.getVentes())
                        .map(ventes -> ventes.stream()
                                .map(venteMapper::toDto)
                                .toList()
                        ).orElse(List.of())
        );
    }

    private Double calculateTotalVentes(List<Vente> ventes) {
        if (null == ventes) {
            return null;
        }
        return ventes.stream().map(Vente::getTotalVente).reduce(0.0, Double::sum);
    }
}
