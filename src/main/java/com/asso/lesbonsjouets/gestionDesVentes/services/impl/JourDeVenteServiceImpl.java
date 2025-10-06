package com.asso.lesbonsjouets.gestionDesVentes.services.impl;

import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.JourDeVente;
import com.asso.lesbonsjouets.gestionDesVentes.mappers.JourDeVenteMapper;
import com.asso.lesbonsjouets.gestionDesVentes.repositories.JourDeVenteRepository;
import com.asso.lesbonsjouets.gestionDesVentes.services.JourDeVenteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class JourDeVenteServiceImpl implements JourDeVenteService {
    private final JourDeVenteRepository jourDeVenteRepository;
    private final JourDeVenteMapper jourDeVenteMapper;

    public JourDeVenteServiceImpl(JourDeVenteRepository jourDeVenteRepository, JourDeVenteMapper jourDeVenteMapper) {
        this.jourDeVenteRepository = jourDeVenteRepository;
        this.jourDeVenteMapper = jourDeVenteMapper;
    }

    @Override
    public List<JourDeVente> listJourDeVentes() {
        return jourDeVenteRepository.findAll();
    }

    @Override
    public Optional<JourDeVente> getJourDeVente(UUID jourDeVenteId) {
        return jourDeVenteRepository.findById(jourDeVenteId);
    }

    @Override
    public JourDeVente createJourDeVente(JourDeVente jourDeVente) {
        if(null !=  jourDeVente.getId())
        {
            throw new IllegalArgumentException("Jour de vente already has an Id!");
        }
        if(null ==  jourDeVente.getJour()) {
            throw new IllegalArgumentException("Jour de vente must have a jour!");
        }
        LocalDateTime now = LocalDateTime.now();
        JourDeVente jourDeVenteToSave = new JourDeVente(
                null,
                jourDeVente.getJour(),
                jourDeVente.getDescription(),
                null,
                now,
                now
        );
        return jourDeVenteRepository.save(jourDeVenteToSave);
    }

    @Transactional
    @Override
    public JourDeVente updateJourDeVente(UUID jourDeVenteId, JourDeVente jourDeVente) {
        if(null == jourDeVente.getId()) {
            throw new IllegalArgumentException("Jour de vente must have an id!");
        }
        if(!Objects.equals(jourDeVente.getId(), jourDeVenteId)) {
            throw new IllegalArgumentException("Jour de vente Ids do not match!");
        }
        if(null ==  jourDeVente.getJour()) {
            throw new IllegalArgumentException("Jour de vente must have a jour!");
        }

        JourDeVente existingJourDeVente = jourDeVenteRepository.findById(jourDeVenteId)
                .orElseThrow(() -> new IllegalArgumentException("Jour de vente not found!"));
        existingJourDeVente.setJour(jourDeVente.getJour());
        existingJourDeVente.setDescription(jourDeVente.getDescription());
        existingJourDeVente.setUpdated(LocalDateTime.now());

        return jourDeVenteRepository.save(existingJourDeVente);
    }

    @Override
    public void deleteJourDeVente(UUID jourDeVenteId) {
        jourDeVenteRepository.deleteById(jourDeVenteId);
    }
}
