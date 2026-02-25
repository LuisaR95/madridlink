package com.example.madridlink.controller;

import com.example.madridlink.model.Consejo;
import com.example.madridlink.model.Tramite;
import com.example.madridlink.model.Documento;
import com.example.madridlink.repository.SedeRepository;
import com.example.madridlink.repository.TramiteRepository;
import com.example.madridlink.repository.DocumentoRepository;
import com.example.madridlink.repository.ConsejoRepository; // <-- 1. AÑADIDO ESTE IMPORT
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
    private ConsejoRepository consejoRepository; // <-- Inyectado correctamente

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tramites", tramiteRepository.findAll());
        return "index";
    }

    @GetMapping("/tramite/{id}")
    public String verDetalles(@PathVariable Long id, Model model) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) return "redirect:/";
        model.addAttribute("tramite", tramite);
        model.addAttribute("documentos", tramite.getDocumentos());
        return "detalle";
    }

    @PostMapping("/documento/marcar/{id}")
    public String marcarDocumento(@PathVariable Long id) {
        Documento doc = documentoRepository.findById(id).orElse(null);
        if (doc != null) {
            doc.setMarcado(!doc.isMarcado());
            documentoRepository.save(doc);
            return "redirect:/tramite/" + doc.getTramite().getId() + "#lista-documentos";
        }
        return "redirect:/";
    }

    @GetMapping("/sedes/buscar")
    public String buscarSedes(@RequestParam(name = "q", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            model.addAttribute("sedes", sedeRepository.findByNombreContaining(query));
        } else {
            model.addAttribute("sedes", sedeRepository.findAll());
        }
        return "sedes";
    }

    @GetMapping("/comunidad")
    public String comunidad(Model model) {
        model.addAttribute("consejos", consejoRepository.findAll());
        model.addAttribute("nuevoConsejo", new Consejo());
        // Añadimos esto para que el desplegable tenga opciones:
        model.addAttribute("todosLosTramites", tramiteRepository.findAll());
        return "comunidad";
    }

    @PostMapping("/comunidad/publicar")
    public String publicarConsejo(@ModelAttribute Consejo consejo) {
        consejoRepository.save(consejo);
        return "redirect:/comunidad";
    }
}