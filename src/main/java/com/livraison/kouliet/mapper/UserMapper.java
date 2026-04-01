package com.livraison.kouliet.mapper;

import com.livraison.kouliet.dto.UserDTO;
import com.livraison.kouliet.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /* ENTITY → DTO */
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setTelephone(user.getTelephone());
        dto.setEmail(user.getEmail());
        dto.setAdresse(user.getAdresse());
        dto.setRole(dto.getRole());

        return dto;
    }

    /* DTO → ENTITY */
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setTelephone(dto.getTelephone());
        user.setEmail(dto.getEmail());
        user.setAdresse(dto.getAdresse());
        user.setRole(dto.getRole());

        return user;
    }

    /* UPDATE ENTITY FROM DTO */
    public void updateEntityFromDto(UserDTO dto, User user) {
        if (dto == null || user == null) {
            return;
        }
        // On met à jour seulement les champs non nulls
        if (dto.getNom() != null) {
            user.setNom(dto.getNom());
        }
        if (dto.getPrenom() != null) {
            user.setPrenom(dto.getPrenom());
        }
        if (dto.getTelephone() != null) {
            user.setTelephone(dto.getTelephone());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getAdresse() != null) {
            user.setAdresse(dto.getAdresse());
        }
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
    }
}
