package com.asso.lesbonsjouets.gestionDesVentes.repositories;

import com.asso.lesbonsjouets.gestionDesVentes.domain.entities.JourDeVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JourDeVenteRepository extends JpaRepository<JourDeVente, UUID> {
}
