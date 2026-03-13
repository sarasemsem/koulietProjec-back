package com.livraison.kouliet.dto;

import com.livraison.kouliet.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
    private Role role; // ADMIN / USER / MANAGER
}