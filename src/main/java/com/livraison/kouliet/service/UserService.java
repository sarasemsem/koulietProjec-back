package com.livraison.kouliet.service;

import com.livraison.kouliet.dto.UserDTO;
import com.livraison.kouliet.exception.ResourceNotFoundException;
import com.livraison.kouliet.mapper.UserMapper;
import com.livraison.kouliet.model.User;
import com.livraison.kouliet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    /* CREATE UTILISATEUR */
    public UserDTO creerUtilisateur(UserDTO dto) {

        User user = mapper.toEntity(dto);
        // Valeurs par défaut
        //user.setActif(true);

        // Normaliser le rôle
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
        User saved = repository.save(user);

        return mapper.toDto(saved);
    }

    /*  LIST ALL*/
    public List<UserDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    /* GET BY ID */
    public UserDTO findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        return mapper.toDto(user);
    }

    /* UPDATE UTILISATEUR */
    public UserDTO update(Long id, UserDTO dto) {

        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        mapper.updateEntityFromDto(dto, user);

        return mapper.toDto(repository.save(user));
    }

    /* DESACTIVER UTILISATEUR */
    public UserDTO desactiver(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

       // user.setActif(false);

        return mapper.toDto(repository.save(user));
    }

    /* GET BY ROLE  */
    public List<UserDTO> findByRole(String role) {
        return repository.findByRole(role.toUpperCase())
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
