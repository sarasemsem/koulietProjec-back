package com.livraison.kouliet.repository;

import com.livraison.kouliet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 🔹 Trouver par rôle
    List<User> findByRole(String role);

    // 🔹 (Optionnel) Trouver par email
    Optional<User> findByEmail(String email);

    // 🔹 (Optionnel) Vérifier existence par email
    boolean existsByEmail(String email);
}