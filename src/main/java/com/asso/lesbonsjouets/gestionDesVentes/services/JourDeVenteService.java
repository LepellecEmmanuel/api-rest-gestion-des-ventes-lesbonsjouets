package com.asso.lesbonsjouets.gestionDesVentes.services;


import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.JourDeVente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JourDeVenteService {
    List<JourDeVente> listJourDeVentes();
    Optional<JourDeVente> getJourDeVente(UUID jourDeVenteId);
    JourDeVente createJourDeVente(JourDeVente jourDeVente);
    JourDeVente updateJourDeVente(UUID jourDeVenteId, JourDeVente jourDeVente);
    void deleteJourDeVente(UUID jourDeVenteId);
}
