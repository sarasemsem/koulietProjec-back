package com.livraison.kouliet.service;

import com.livraison.kouliet.dto.BonDeLivraisonDTO;
import com.livraison.kouliet.enumRep.StatutCommande;
import com.livraison.kouliet.exception.ResourceNotFoundException;
import com.livraison.kouliet.mapper.BonDeLivraisonMapper;
import com.livraison.kouliet.model.*;
import com.livraison.kouliet.repository.AdresseRepository;
import com.livraison.kouliet.repository.BonDeLivraisonRepository;
import com.livraison.kouliet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BonDeLivraisonService {

    private final BonDeLivraisonRepository repository;
    private final BonDeLivraisonMapper mapper;
    private final UserRepository userRepository;
    private final AdresseRepository adresseRepository;

    /* ===============================
       CREATE COMMANDE
       =============================== */
    public BonDeLivraisonDTO creerCommande(BonDeLivraisonDTO dto) {

        // DTO ➜ ENTITY
        BonDeLivraison bonDeLivraison = mapper.toEntity(dto);

        // Backend-managed fields
        bonDeLivraison.setTrackingNumber(TrackingGenerator.generate());
        bonDeLivraison.setDateCreation(new Date());
        bonDeLivraison.setStatut(StatutCommande.A_RAMASSER.name());

        BigDecimal total = bonDeLivraison.getMontantTtc()
                .add(bonDeLivraison.getLivraison() != null ? bonDeLivraison.getLivraison() : BigDecimal.ZERO);

        bonDeLivraison.setTotalAPayer(total);

        // Attach User
        if (dto.getExpediteurId() != null) {
            User exp = userRepository.findById(dto.getExpediteurId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Expediteur not found")
                    );
            bonDeLivraison.setExpediteur(exp);
        }

        // Attach Adresse Livraison
        if (dto.getAdresseLivraisonId() != null) {
            Adresse adr = adresseRepository.findById(dto.getAdresseLivraisonId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Adresse not found")
                    );
            bonDeLivraison.setAdresseLivraison(adr);
        }

        BonDeLivraison saved = repository.save(bonDeLivraison);

        return mapper.toDto(saved);
    }
    // ✅ LIST ALL
    public List<BonDeLivraisonDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    // ✅ GET BY ID (for edit/view)
    public BonDeLivraisonDTO findById(Long id) {
        BonDeLivraison bonDeLivraison = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));
        return mapper.toDto(bonDeLivraison);
    }

    public BonDeLivraisonDTO update(Long id, BonDeLivraisonDTO dto) {
        BonDeLivraison commande = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));

        mapper.updateEntityFromDto(dto, commande);

        return mapper.toDto(repository.save(commande));
    }
    /* ===============================
       CHANGE STATUS
       =============================== */
    public BonDeLivraison changerStatut(Long id, StatutCommande nouveauStatut) {

        BonDeLivraison commande = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Commande introuvable")
                );

        HistoriqueStatut hist = new HistoriqueStatut();
        hist.setDateChangement(LocalDateTime.now());
        hist.setAncienStatut(StatutCommande.valueOf(commande.getStatut()));
        hist.setNouveauStatut(nouveauStatut);
        hist.setBonDeLivraison(commande);

        commande.getHistoriqueStatuts().add(hist);
        commande.setStatut(nouveauStatut.name());

        return repository.save(commande);
    }

    /* ===============================
       GET BY TRACKING
       =============================== */
    public BonDeLivraisonDTO getByTracking(String trackingNumber) {
        BonDeLivraison bonDeLivraison = repository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BonDeCommande introuvable avec tracking: " + trackingNumber
                        )
                );

        return mapper.toDto(bonDeLivraison);
    }
}
