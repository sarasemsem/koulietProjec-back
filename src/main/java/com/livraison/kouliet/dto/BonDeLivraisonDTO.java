package com.livraison.kouliet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BonDeLivraisonDTO {

    private Long id;

    // Destinataire
    private String nom;
    private String prenom;
    private String telephone;
    private String gouvernorat;
    private String adresse;

    // Article
    private String designation;
    private Integer quantite;

    // Montants
    private BigDecimal montantHt;
    private BigDecimal tva;
    private BigDecimal montantTtc;
    private BigDecimal livraison;
    private BigDecimal poids;
    private BigDecimal totalAPayer;

    // Backend
    private Date dateCreation;
    private String statut;
    private String trackingNumber;

    // 🔗 Relations (ONLY IDs)
    private Long expediteurId;
    private Long adresseLivraisonId;

   // private List<ColisDTO> colis;
   // private List<HistoriqueStatutDTO> historiqueStatuts;

}