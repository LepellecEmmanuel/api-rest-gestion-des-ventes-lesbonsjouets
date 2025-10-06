package com.asso.lesbonsjouets.gestionDesVentes.services.impl;

import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.JourDeVente;
import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.Vente;
import com.asso.lesbonsjouets.gestionDesVentes.mappers.VenteMapper;
import com.asso.lesbonsjouets.gestionDesVentes.repositories.JourDeVenteRepository;
import com.asso.lesbonsjouets.gestionDesVentes.repositories.VenteRepository;
import com.asso.lesbonsjouets.gestionDesVentes.services.VenteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class VenteServiceImpl implements VenteService {
    private final VenteRepository venteRepository;
    private final VenteMapper venteMapper;
    private final JourDeVenteRepository jourDeVenteRepository;

    public VenteServiceImpl(VenteRepository venteRepository, VenteMapper venteMapper, JourDeVenteRepository jourDeVenteRepository) {
        this.venteRepository = venteRepository;
        this.venteMapper = venteMapper;
        this.jourDeVenteRepository = jourDeVenteRepository;
    }

    @Override
    public List<Vente> listVentes(UUID jourDeVenteId) {
        return venteRepository.findByJourDeVenteId(jourDeVenteId);
    }

    @Override
    public Optional<Vente> getVente(UUID jourDeVenteId, UUID venteId) {
        return venteRepository.findByJourDeVenteIdAndId(jourDeVenteId, venteId);
    }

    @Transactional
    @Override
    public Vente createVente(UUID jourDeVenteID, Vente vente) {
        if(null != vente.getId()) {
            throw new IllegalArgumentException("Vente already has an Id!");
        }
        if(null == vente.getTypeDeVente()) {
            throw new IllegalArgumentException("Type of Vente is required!");
        }
        JourDeVente jourDeVente = jourDeVenteRepository.findById(jourDeVenteID)
                .orElseThrow(() -> new IllegalArgumentException("Jour de Vente not found!"));
        Double totalVente = Optional.ofNullable(vente.getTotalVente()).orElse(0.0);
        LocalDateTime now = LocalDateTime.now();
        Vente venteToSave = new Vente(
                null,
                vente.getTypeDeVente(),
                vente.getDescription(),
                totalVente,
                jourDeVente,
                now,
                now
        );
        return venteRepository.save(venteToSave);
    }

    @Transactional
    @Override
    public Vente updateVente(UUID jourDeVenteId, UUID venteId, Vente vente) {
        if(null == vente.getId()) {
            throw new IllegalArgumentException("Vente id is required!");
        }
        if(!Objects.equals(venteId, vente.getId())) {
            throw new IllegalArgumentException("Vente Ids do not match!");
        }
        if(null == vente.getTypeDeVente()) {
            throw new IllegalArgumentException("Type of Vente is required!");
        }
        Vente existingVente = venteRepository.findByJourDeVenteIdAndId(jourDeVenteId, venteId)
                .orElseThrow(() -> new IllegalArgumentException("Vente not found!"));
        existingVente.setTypeDeVente(vente.getTypeDeVente());
        existingVente.setDescription(vente.getDescription());
        existingVente.setTotalVente(vente.getTotalVente());
        existingVente.setUpdated(LocalDateTime.now());
        return venteRepository.save(existingVente);
    }

    @Transactional
    @Override
    public void deleteVente(UUID jourDeVenteId, UUID venteId) {
        venteRepository.deleteByJourDeVenteIdAndId(jourDeVenteId, venteId);
    }
}
