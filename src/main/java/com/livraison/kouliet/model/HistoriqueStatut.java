package com.livraison.kouliet.model;

import com.livraison.kouliet.enumRep.StatutCommande;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class HistoriqueStatut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateChangement;

    @Enumerated(EnumType.STRING)
    private StatutCommande ancienStatut;

    @Enumerated(EnumType.STRING)
    private StatutCommande nouveauStatut;

    @ManyToOne
    private BonDeCommande bonDeCommande;
}
