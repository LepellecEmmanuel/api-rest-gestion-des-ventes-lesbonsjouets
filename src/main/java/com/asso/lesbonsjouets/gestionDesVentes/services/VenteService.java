package com.asso.lesbonsjouets.gestionDesVentes.services;

import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.Vente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VenteService {
    List<Vente> listVentes(UUID jourDeVenteId);
    Optional<Vente> getVente(UUID jourDeVenteId, UUID venteId);
    Vente createVente(UUID jourDeVenteID, Vente vente);
    Vente updateVente(UUID jourDeVenteId, UUID venteId, Vente vente);
    void deleteVente(UUID jourDeVenteId, UUID venteId);
}
