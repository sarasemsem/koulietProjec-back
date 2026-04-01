package com.livraison.kouliet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String MF;

    @Column(nullable = false)
    private String password;

    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;

    @Enumerated(EnumType.STRING)
    private Role role;

    // ✅ Implémentation correcte
    // ✅ AJOUT IMPORTANT
    private boolean actif = true;

    // ✅ Convertir String ➜ Enum Role
    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }
}