package com.livraison.kouliet.repository;

import com.livraison.kouliet.model.Expediteur;
import com.livraison.kouliet.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpediteurRepository extends JpaRepository<Expediteur, Long> {
}
