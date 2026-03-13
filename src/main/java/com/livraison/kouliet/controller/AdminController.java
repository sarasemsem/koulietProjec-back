package com.livraison.kouliet.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @GetMapping("/test")
    public String adminOnly() {
        return "ADMIN ACCESS";
    }
}



