package com.asso.lesbonsjouets.gestionDesVentes.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name="ventes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vente {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="type_de_vente", nullable = false)
    private TypeDeVente typeDeVente;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jour_de_vente_id")
    JourDeVente jourDeVente;
}
