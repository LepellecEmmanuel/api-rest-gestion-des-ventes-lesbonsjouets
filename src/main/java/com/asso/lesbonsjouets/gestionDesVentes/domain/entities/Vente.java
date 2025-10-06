package com.asso.lesbonsjouets.gestionDesVentes.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Column(name="total_vente", nullable = false)
    private Double totalVente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jour_de_vente_id")
    JourDeVente jourDeVente;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;


}
