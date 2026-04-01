package com.livraison.kouliet.dto;

import lombok.Getter;

public class UserDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String Email;
    private String adresse;
    @Getter
    private String role;


}
