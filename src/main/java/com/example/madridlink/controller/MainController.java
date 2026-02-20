package com.example.madridlink.controller;

import com.example.madridlink.repository.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Cambiamos de RestController a Controller para usar HTML
public class MainController {

    @Autowired
    private TramiteRepository tramiteRepository;

    @GetMapping("/")
    public String index(Model model) {
        // Buscamos todos los trámites de la DB y los pasamos al HTML
        model.addAttribute("tramites", tramiteRepository.findAll());
        return "index"; // Esto buscará un archivo llamado index.html
    }
}