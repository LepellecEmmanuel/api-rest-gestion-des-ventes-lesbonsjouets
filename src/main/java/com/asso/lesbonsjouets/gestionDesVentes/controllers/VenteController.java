package com.asso.lesbonsjouets.gestionDesVentes.controllers;

import com.asso.lesbonsjouets.gestionDesVentes.domain.dto.VenteDto;
import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.Vente;
import com.asso.lesbonsjouets.gestionDesVentes.mappers.VenteMapper;
import com.asso.lesbonsjouets.gestionDesVentes.repositories.VenteRepository;
import com.asso.lesbonsjouets.gestionDesVentes.services.VenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/jours_de_vente/{jour_de_vente_id}/ventes")
public class VenteController {
    private final VenteService venteService;
    private final VenteMapper venteMapper;

    public VenteController(VenteService venteService, VenteMapper venteMapper) {
        this.venteService = venteService;
        this.venteMapper = venteMapper;
    }

    @GetMapping()
    public List<VenteDto> listaVentes(@PathVariable("jour_de_vente_id") UUID jourDeVenteId) {
        return venteService.listVentes(jourDeVenteId)
                .stream()
                .map(venteMapper::toDto)
                .toList();
    }

    @GetMapping(path = "/{vente_id}")
    public Optional<VenteDto> getVente(@PathVariable("jour_de_vente_id") UUID jourDeVenteId, @PathVariable("vente_id") UUID venteId) {
        return venteService.getVente(jourDeVenteId, venteId).map(venteMapper::toDto);
    }

    @PostMapping()
    public VenteDto createVente(
            @PathVariable("jour_de_vente_id") UUID jourDeVenteId,
            @RequestBody VenteDto venteDto
    ) {
        Vente createdVente = venteService.createVente(jourDeVenteId, venteMapper.fromDto(venteDto));
        return venteMapper.toDto(createdVente);
    }

    @PutMapping(path = "/{vente_id}")
    public VenteDto updateVente(
            @PathVariable("jour_de_vente_id") UUID jourVenteId,
            @PathVariable("vente_id") UUID venteId,
            @RequestBody VenteDto venteDto
    ) {
        Vente updatedVente = venteService.updateVente(jourVenteId, venteId, venteMapper.fromDto(venteDto));
        return venteMapper.toDto(updatedVente);
    }

    @DeleteMapping(path = "/{vente_id}")
    public void deleteVente(
            @PathVariable("jour_de_vente_id") UUID jourVenteId,
            @PathVariable("vente_id") UUID venteId
    ) {
        venteService.deleteVente(jourVenteId, venteId);
    }
}
