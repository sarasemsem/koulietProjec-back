package com.livraison.kouliet.controller;

import com.livraison.kouliet.dto.BonDeCommandeDTO;
import com.livraison.kouliet.enumRep.StatutCommande;
import com.livraison.kouliet.mapper.BonDeCommandeMapper;
import com.livraison.kouliet.model.BonDeCommande;
import com.livraison.kouliet.service.BonDeCommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bon-commandes")
@RequiredArgsConstructor
public class BonDeCommandeController {

    private final BonDeCommandeService service;
    private final BonDeCommandeMapper mapper;

    @PostMapping
    public BonDeCommandeDTO creer(@RequestBody BonDeCommandeDTO dto) {
        return service.creerCommande(dto);
    }

    @PutMapping("/{id}/statut")
    public BonDeCommandeDTO changerStatut(
            @PathVariable Long id,
            @RequestParam StatutCommande statut) {
        return mapper.toDto(service.changerStatut(id, statut));
    }

    @GetMapping("/tracking/{tracking}")
    public BonDeCommandeDTO suivreCommande(@PathVariable String tracking) {
        return service.getByTracking(tracking);
    }
    @GetMapping
    public List<BonDeCommandeDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BonDeCommandeDTO get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public BonDeCommandeDTO update(@PathVariable Long id,
                                   @RequestBody BonDeCommandeDTO dto) {
        return service.update(id, dto);
    }
}
