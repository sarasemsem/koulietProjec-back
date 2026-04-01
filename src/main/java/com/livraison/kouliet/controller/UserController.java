package com.livraison.kouliet.controller;

import com.livraison.kouliet.dto.UserDTO;
import com.livraison.kouliet.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;
import com.livraison.kouliet.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*") // pour Angular

public class UserController {

    private final UserService service;

    public UserController(UserService service ) {
        this.service = service;
    }


    // 🔹 1. Créer utilisateur
    @PostMapping
    public UserDTO creer(@RequestBody UserDTO dto) {
        return service.creerUtilisateur(dto);
    }

    // 🔹 2. Modifier utilisateur
    @PutMapping("/{id}")
    public UserDTO modifier(@PathVariable Long id, @RequestBody UserDTO dto) {
        return service.update(id, dto);    }

    // 🔹 3. Liste des utilisateurs
    @GetMapping
    public List<UserDTO> list() {

        return service.findAll();
    }

    // 🔹 4. Consulter par ID
    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // 🔹 5. Chercher par rôle
    @GetMapping("/role/{role}")
    public List<UserDTO> getByRole(@PathVariable String role) {
        return service.findByRole(role);
    }

    // 🔹 6. Désactiver utilisateur
    @PutMapping("/desactiver/{id}")
    public UserDTO desactiver(@PathVariable Long id) {

        return service.desactiver(id);
    }
}
