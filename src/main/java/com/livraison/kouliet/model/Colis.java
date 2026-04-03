package com.livraison.kouliet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Colis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double poids;
    private Boolean fragile;

    @ManyToOne
    private BonDeLivraison bonDeLivraison;
}
