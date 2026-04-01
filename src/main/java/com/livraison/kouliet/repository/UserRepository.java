package com.livraison.kouliet.repository;

import com.livraison.kouliet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 🔹 Trouver par rôle
    List<User> findByRole(String role);

    // 🔹 (Optionnel) Trouver par email
    User findByEmail(String email);

    // 🔹 (Optionnel) Vérifier existence par email
    boolean existsByEmail(String email);
}