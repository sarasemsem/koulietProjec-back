package com.livraison.kouliet.dto;

import com.livraison.kouliet.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String matriculeFiscale;
    private String password;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private Role role;
    private boolean actif = false;
}