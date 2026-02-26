package com.example.madridlink.controller;

import com.example.madridlink.model.Consejo;
import com.example.madridlink.model.Tramite;
import com.example.madridlink.model.Documento;
import com.example.madridlink.repository.SedeRepository;
import com.example.madridlink.repository.TramiteRepository;
import com.example.madridlink.repository.DocumentoRepository;
import com.example.madridlink.repository.ConsejoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private TramiteRepository tramiteRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private ConsejoRepository consejoRepository;

    // --- PÁGINA DE INICIO ---
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tramites", tramiteRepository.findAll());
        return "index";
    }

    // --- DETALLE DEL TRÁMITE ---
    @GetMapping("/tramite/{id}")
    public String verDetalles(@PathVariable Long id, Model model) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) return "redirect:/";
        model.addAttribute("tramite", tramite);
        model.addAttribute("documentos", tramite.getDocumentos());
        return "detalle";
    }

    // --- GESTIÓN DE DOCUMENTOS (CHECKBOX) ---
    @PostMapping("/documento/marcar/{id}")
    public String marcarDocumento(@PathVariable Long id) {
        Documento doc = documentoRepository.findById(id).orElse(null);
        if (doc != null && doc.getTramite() != null) {
            doc.setMarcado(!doc.isMarcado());
            documentoRepository.save(doc);
            return "redirect:/tramite/" + doc.getTramite().getId() + "#lista-documentos";
        }
        return "redirect:/";
    }

    // Seguro para evitar Error 405 si se refresca la página
    @GetMapping("/documento/marcar/{id}")
    public String capturarErrorGet(@PathVariable Long id) {
        return "redirect:/tramite/" + id;
    }

    // --- COMUNIDAD: PUBLICAR CONSEJO ---
    @PostMapping("/comunidad/publicar")
    public String publicarConsejo(@ModelAttribute Consejo consejo, @RequestParam(name = "tramiteId", required = false) Long tramiteId) {

        // Buscamos el trámite para vincularlo al consejo
        if (tramiteId != null) {
            Tramite tramite = tramiteRepository.findById(tramiteId).orElse(null);
            consejo.setTramite(tramite);
        } else if (consejo.getTramite() != null && consejo.getTramite().getId() != null) {
            Tramite tramite = tramiteRepository.findById(consejo.getTramite().getId()).orElse(null);
            consejo.setTramite(tramite);
        }

        // Guardamos si tiene contenido
        if (consejo.getContenido() != null && !consejo.getContenido().trim().isEmpty()) {
            consejoRepository.save(consejo);
        }

        return "redirect:/comunidad";
    }

    // --- BUSCADOR DE SEDES ---
    @GetMapping("/sedes/buscar")
    public String buscarSedes(@RequestParam(name = "q", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            // Cambiamos a findByNombreContainingIgnoreCase
            model.addAttribute("sedes", sedeRepository.findByNombreContainingIgnoreCase(query));
        } else {
            model.addAttribute("sedes", sedeRepository.findAll());
        }
        // Guardamos la query para que el buscador no se vacíe al dar clic
        model.addAttribute("query", query);
        return "sedes";
    }
    // --- VER COMUNIDAD ---
    @GetMapping("/comunidad")
    public String comunidad(Model model) {
        model.addAttribute("consejos", consejoRepository.findAll());
        model.addAttribute("nuevoConsejo", new Consejo());
        model.addAttribute("todosLosTramites", tramiteRepository.findAll());
        return "comunidad";
    }
}