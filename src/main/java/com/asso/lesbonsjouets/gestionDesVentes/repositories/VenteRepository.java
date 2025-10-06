package com.asso.lesbonsjouets.gestionDesVentes.repositories;

import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VenteRepository extends JpaRepository<Vente, UUID> {
    List<Vente> findByJourDeVenteId(UUID jourDeVenteId);
    Optional<Vente> findByJourDeVenteIdAndId(UUID jourDeVenteId, UUID venteId);
    void  deleteByJourDeVenteIdAndId(UUID jourDeVenteId, UUID venteId);
}
