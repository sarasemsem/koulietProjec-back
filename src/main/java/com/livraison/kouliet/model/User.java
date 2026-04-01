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

    // ⚠️ nom peu clair → à améliorer
    @Column(name = "mf")
    private String mf;

    @Column(nullable = false)
    private String password;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 🔹 utilisateur actif ou non
    //private boolean actif = true;


}