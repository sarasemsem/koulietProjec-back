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
    @Column(name = "mf")
    private String matriculeFiscale;
    @Column(nullable = true)
    private String password;
    @Column()
    private String nom;
    @Column()
    private String prenom;
    @Column()
    private String telephone;
    @Column()
    private String adresse;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private boolean actif = false;


}