package com.livraison.kouliet.mapper;

import com.livraison.kouliet.dto.BonDeCommandeDTO;
import com.livraison.kouliet.model.BonDeCommande;
import org.springframework.stereotype.Component;

@Component
public class BonDeCommandeMapper {

    /* =========================
       ENTITY ➜ DTO
       ========================= */
    public BonDeCommandeDTO toDto(BonDeCommande entity) {
        if (entity == null) return null;

        BonDeCommandeDTO dto = new BonDeCommandeDTO();

        dto.setId(entity.getId());

        // Destinataire
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setTelephone(entity.getTelephone());
        dto.setGouvernorat(entity.getGouvernorat());
        dto.setAdresse(entity.getAdresse());

        // Article
        dto.setDesignation(entity.getDesignation());
        dto.setQuantite(entity.getQuantite());

        // Montants
        dto.setMontantHt(entity.getMontantHt());
        dto.setTva(entity.getTva());
        dto.setMontantTtc(entity.getMontantTtc());
        dto.setLivraison(entity.getLivraison());
        dto.setPoids(entity.getPoids());
        dto.setTotalAPayer(entity.getTotalAPayer());

        // Backend
        dto.setDateCreation(entity.getDateCreation());
        dto.setStatut(entity.getStatut());
        dto.setTrackingNumber(entity.getTrackingNumber());

        return dto;
    }

    /* =========================
       DTO ➜ ENTITY
       ========================= */
    public BonDeCommande toEntity(BonDeCommandeDTO dto) {
        if (dto == null) return null;

        BonDeCommande entity = new BonDeCommande();

        entity.setId(dto.getId());

        // Destinataire
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setTelephone(dto.getTelephone());
        entity.setGouvernorat(dto.getGouvernorat());
        entity.setAdresse(dto.getAdresse());

        // Article
        entity.setDesignation(dto.getDesignation());
        entity.setQuantite(dto.getQuantite());

        // Montants
        entity.setMontantHt(dto.getMontantHt());
        entity.setTva(dto.getTva());
        entity.setMontantTtc(dto.getMontantTtc());
        entity.setLivraison(dto.getLivraison());
        entity.setPoids(dto.getPoids());
        entity.setTotalAPayer(dto.getTotalAPayer());
        return entity;
    }
    public void updateEntityFromDto(BonDeCommandeDTO d, BonDeCommande e) {
        e.setNom(d.getNom());
        e.setPrenom(d.getPrenom());
        e.setTelephone(d.getTelephone());
        e.setAdresse(d.getAdresse());
        e.setGouvernorat(d.getGouvernorat());
        e.setDesignation(d.getDesignation());
        e.setQuantite(d.getQuantite());
        e.setMontantHt(d.getMontantHt());
        e.setTva(d.getTva());
        e.setMontantTtc(d.getMontantTtc());
        e.setLivraison(d.getLivraison());
        e.setPoids(d.getPoids());
        // ❌ tracking + statut not changed here
    }
}
