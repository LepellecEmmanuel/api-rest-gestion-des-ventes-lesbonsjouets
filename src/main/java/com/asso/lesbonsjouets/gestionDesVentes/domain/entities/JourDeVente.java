package com.asso.lesbonsjouets.gestionDesVentes.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="jours_de_vente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JourDeVente {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "jour", nullable = false, unique = true)
    private LocalDate jour;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "jourDeVente", cascade = {
            CascadeType.REMOVE, CascadeType.PERSIST
    })
    List<Vente> ventes;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;
}
