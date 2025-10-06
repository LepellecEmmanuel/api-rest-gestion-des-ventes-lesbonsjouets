package com.asso.lesbonsjouets.gestionDesVentes.controllers;

import com.asso.lesbonsjouets.gestionDesVentes.domain.dto.JourDeVenteDto;
import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.JourDeVente;
import com.asso.lesbonsjouets.gestionDesVentes.mappers.JourDeVenteMapper;
import com.asso.lesbonsjouets.gestionDesVentes.services.JourDeVenteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/jours_de_vente")
public class JourDeVenteController {
    private final JourDeVenteService jourDeVenteService;
    private  final JourDeVenteMapper jourDeVenteMapper;

    public JourDeVenteController(JourDeVenteService jourDeVenteService, JourDeVenteMapper jourDeVenteMapper) {
        this.jourDeVenteService = jourDeVenteService;
        this.jourDeVenteMapper = jourDeVenteMapper;
    }

    @GetMapping
    public List<JourDeVenteDto> listJourDeVentes() {
        return jourDeVenteService.listJourDeVentes()
                .stream()
                .map(jourDeVenteMapper::toDto)
                .toList();
    }
}
