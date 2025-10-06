package com.asso.lesbonsjouets.gestionDesVentes.services.impl;

import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.JourDeVente;
import com.asso.lesbonsjouets.gestionDesVentes.mappers.JourDeVenteMapper;
import com.asso.lesbonsjouets.gestionDesVentes.repositories.JourDeVenteRepository;
import com.asso.lesbonsjouets.gestionDesVentes.services.JourDeVenteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public JourDeVente updateJourDeVente(UUID jourDeVenteId, JourDeVente jourDeVente) {
        return null;
    }

    @Override
    public void deleteJourDeVente(UUID jourDeVenteId) {

    }
}
