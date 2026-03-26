package com.livraison.kouliet.service;

import com.livraison.kouliet.dto.BonDeCommandeDTO;
import com.livraison.kouliet.enumRep.StatutCommande;
import com.livraison.kouliet.exception.ResourceNotFoundException;
import com.livraison.kouliet.mapper.BonDeCommandeMapper;
import com.livraison.kouliet.model.*;
import com.livraison.kouliet.repository.AdresseRepository;
import com.livraison.kouliet.repository.BonDeLivraisonRepository;
import com.livraison.kouliet.repository.ExpediteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BonDeCommandeService {

    private final BonDeLivraisonRepository repository;
    private final BonDeCommandeMapper mapper;
    private final ExpediteurRepository expediteurRepository;
    private final AdresseRepository adresseRepository;

    /* ===============================
       CREATE COMMANDE
       =============================== */
    public BonDeCommandeDTO creerCommande(BonDeCommandeDTO dto) {

        // DTO ➜ ENTITY
        BonDeCommande commande = mapper.toEntity(dto);

        // Backend-managed fields
        commande.setTrackingNumber(TrackingGenerator.generate());
        commande.setDateCreation(new Date());
        commande.setStatut(StatutCommande.A_RAMASSER.name());

        BigDecimal total = commande.getMontantTtc()
                .add(commande.getLivraison() != null ? commande.getLivraison() : BigDecimal.ZERO);

        commande.setTotalAPayer(total);

        // Attach Expediteur
        if (dto.getExpediteurId() != null) {
            Expediteur exp = expediteurRepository.findById(dto.getExpediteurId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Expediteur not found")
                    );
            commande.setExpediteur(exp);
        }

        // Attach Adresse Livraison
        if (dto.getAdresseLivraisonId() != null) {
            Adresse adr = adresseRepository.findById(dto.getAdresseLivraisonId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Adresse not found")
                    );
            commande.setAdresseLivraison(adr);
        }

        BonDeCommande saved = repository.save(commande);

        return mapper.toDto(saved);
    }
    // ✅ LIST ALL
    public List<BonDeCommandeDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    // ✅ GET BY ID (for edit/view)
    public BonDeCommandeDTO findById(Long id) {
        BonDeCommande commande = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));
        return mapper.toDto(commande);
    }

    public BonDeCommandeDTO update(Long id, BonDeCommandeDTO dto) {
        BonDeCommande commande = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));

        mapper.updateEntityFromDto(dto, commande);

        return mapper.toDto(repository.save(commande));
    }
    /* ===============================
       CHANGE STATUS
       =============================== */
    public BonDeCommande changerStatut(Long id, StatutCommande nouveauStatut) {

        BonDeCommande commande = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Commande introuvable")
                );

        HistoriqueStatut hist = new HistoriqueStatut();
        hist.setDateChangement(LocalDateTime.now());
        hist.setAncienStatut(StatutCommande.valueOf(commande.getStatut()));
        hist.setNouveauStatut(nouveauStatut);
        hist.setBonDeCommande(commande);

        commande.getHistoriqueStatuts().add(hist);
        commande.setStatut(nouveauStatut.name());

        return repository.save(commande);
    }

    /* ===============================
       GET BY TRACKING
       =============================== */
    public BonDeCommandeDTO getByTracking(String trackingNumber) {
        BonDeCommande commande = repository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BonDeCommande introuvable avec tracking: " + trackingNumber
                        )
                );

        return mapper.toDto(commande);
    }
}
