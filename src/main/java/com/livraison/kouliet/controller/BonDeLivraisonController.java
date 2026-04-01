package com.livraison.kouliet.controller;

import com.livraison.kouliet.dto.BonDeLivraisonDTO;
import com.livraison.kouliet.enumRep.StatutCommande;
import com.livraison.kouliet.mapper.BonDeLivraisonMapper;
import com.livraison.kouliet.service.BonDeLivraisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bon-commandes")
@RequiredArgsConstructor
public class BonDeLivraisonController {

    private final BonDeLivraisonService service;
    private final BonDeLivraisonMapper mapper;

    @PostMapping
    public BonDeLivraisonDTO creer(@RequestBody BonDeLivraisonDTO dto) {
        return service.creerCommande(dto);
    }

    @PutMapping("/{id}/statut")
    public BonDeLivraisonDTO changerStatut(
            @PathVariable Long id,
            @RequestParam StatutCommande statut) {
        return mapper.toDto(service.changerStatut(id, statut));
    }

    @GetMapping("/tracking/{tracking}")
    public BonDeLivraisonDTO suivreCommande(@PathVariable String tracking) {
        return service.getByTracking(tracking);
    }

    @GetMapping
    public List<BonDeLivraisonDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BonDeLivraisonDTO get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public BonDeLivraisonDTO update(@PathVariable Long id,
                                   @RequestBody BonDeLivraisonDTO dto) {
        return service.update(id, dto);
    }
}
