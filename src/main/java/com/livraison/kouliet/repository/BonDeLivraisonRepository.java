package com.livraison.kouliet.repository;

import com.livraison.kouliet.model.BonDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BonDeCommandeRepository extends JpaRepository<BonDeCommande, Long> {
    Optional<BonDeCommande> findByTrackingNumber(String trackingNumber);
}
