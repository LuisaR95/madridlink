package com.example.madridlink.controller;

import com.example.madridlink.model.Consejo;
import com.example.madridlink.model.Tramite;
import com.example.madridlink.repository.ConsejoRepository;
import com.example.madridlink.repository.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ComunidadController {

    @Autowired
    private ConsejoRepository consejoRepository;

    @Autowired
    private TramiteRepository tramiteRepository;

    @GetMapping("/comunidad")
    public String comunidad(Model model) {
        model.addAttribute("consejos", consejoRepository.findAll());
        model.addAttribute("nuevoConsejo", new Consejo());
        model.addAttribute("todosLosTramites", tramiteRepository.findAll());
        return "comunidad"; // Busca el archivo templates/comunidad.html
    }

    @PostMapping("/comunidad/publicar")
    public String publicarConsejo(@ModelAttribute Consejo consejo, 
                                   @RequestParam(name = "tramiteId", required = false) Long tramiteId) {
        if (tramiteId != null) {
            Tramite tramite = tramiteRepository.findById(tramiteId).orElse(null);
            consejo.setTramite(tramite);
        }
        if (consejo.getContenido() != null && !consejo.getContenido().trim().isEmpty()) {
            consejoRepository.save(consejo);
        }
        return "redirect:/comunidad";
    }
}