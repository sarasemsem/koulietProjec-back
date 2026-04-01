package com.livraison.kouliet.dto;

import com.livraison.kouliet.model.Role;

public class UserDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String adresse;
    private Role role;

    // 🔹 GETTERS

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public Role getRole() {
        return role;
    }

    // 🔹 SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}