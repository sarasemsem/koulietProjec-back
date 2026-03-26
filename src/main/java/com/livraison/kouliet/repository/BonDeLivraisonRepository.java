package com.livraison.kouliet.repository;
import com.livraison.kouliet.model.BonDeLivraison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BonDeLivraisonRepository extends JpaRepository<BonDeLivraison, Long> {
    Optional<BonDeLivraison> findByTrackingNumber(String trackingNumber);
}
