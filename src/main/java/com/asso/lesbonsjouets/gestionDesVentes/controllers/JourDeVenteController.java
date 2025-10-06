package com.asso.lesbonsjouets.gestionDesVentes.controllers;

import com.asso.lesbonsjouets.gestionDesVentes.domain.dto.JourDeVenteDto;
import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.JourDeVente;
import com.asso.lesbonsjouets.gestionDesVentes.mappers.JourDeVenteMapper;
import com.asso.lesbonsjouets.gestionDesVentes.services.JourDeVenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping(path = "/{jour_de_vente_id}")
    public Optional<JourDeVenteDto> getJourDeVente(@PathVariable("jour_de_vente_id") UUID jourDeVenteId) {
        return jourDeVenteService.getJourDeVente(jourDeVenteId).map(jourDeVenteMapper::toDto);
    }

    @PostMapping()
    public JourDeVenteDto createJourDeVente(@RequestBody JourDeVenteDto jourDeVenteDto) {
        JourDeVente createdJourDeVente = jourDeVenteService.createJourDeVente(jourDeVenteMapper.fromDto(jourDeVenteDto));
        return jourDeVenteMapper.toDto(createdJourDeVente);
    }

    @PutMapping(path = "/{jour_de_vente_id}")
    public JourDeVenteDto updateJourDeVente(
            @PathVariable("jour_de_vente_id") UUID jourDeVenteId,
            @RequestBody JourDeVenteDto jourDeVenteDto
    ) {
        JourDeVente updatedJourDeVente = jourDeVenteService.updateJourDeVente(jourDeVenteId, jourDeVenteMapper.fromDto(jourDeVenteDto));
        return jourDeVenteMapper.toDto(updatedJourDeVente);
    }

    @DeleteMapping(path = "/{jour_de_vente_id}")
    public void deleteJourDeVente(@PathVariable("jour_de_vente_id") UUID jourDeVenteId) {
        jourDeVenteService.deleteJourDeVente(jourDeVenteId);
    }
}
