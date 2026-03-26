package com.livraison.kouliet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class BonDeLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String trackingNumber;

    private String nom;
    private String prenom;
    private String telephone;
    private String gouvernorat;
    private String adresse;

    private String designation;
    private Integer quantite;

    private BigDecimal montantHt;
    private BigDecimal tva;
    private BigDecimal montantTtc;
    @Column(name = "frais_livraison", nullable = false)
    private BigDecimal livraison = BigDecimal.ZERO;

    private BigDecimal poids;

    private BigDecimal totalAPayer;
    private Date dateCreation ;

    private String statut;
    @ManyToOne
    private Expediteur expediteur;

    @ManyToOne
    private Adresse adresseLivraison;

    @OneToMany(mappedBy = "bonDeLivraison", cascade = CascadeType.ALL)
    private List<Colis> colis;

    @OneToMany(mappedBy = "bonDeLivraison", cascade = CascadeType.ALL)
    private List<HistoriqueStatut> historiqueStatuts;
}
