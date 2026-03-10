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

import java.util.List;

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

    @GetMapping("/")
    public String index(Model model) {
        List<Tramite> tramites = tramiteRepository.findAll();
        long totalDocs = 0;
        long totalListos = 0;

        for (Tramite t : tramites) {
            totalDocs += t.getDocumentos().size();
            totalListos += t.getDocumentos().stream().filter(doc -> doc.isMarcado()).count();
        }

        int porcentajeGlobal = (totalDocs > 0) ? (int) (totalListos * 100 / totalDocs) : 0;

        model.addAttribute("tramites", tramites);
        model.addAttribute("progresoGlobal", porcentajeGlobal);
        return "index";
    }

    @GetMapping("/tramite/{id}")
    public String verDetalles(@PathVariable Long id, Model model) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) return "redirect:/";
        model.addAttribute("tramite", tramite);
        model.addAttribute("documentos", tramite.getDocumentos());
        model.addAttribute("allTramites", tramiteRepository.findAll());
        return "detalle";
    }

    // --- CORREGIDO: Recibe el parámetro 'anchor' ---
    @PostMapping("/documento/marcar/{id}")
    public String marcarDocumento(@PathVariable Long id, @RequestParam(name = "anchor", required = false) String anchor) {
        Documento doc = documentoRepository.findById(id).orElse(null);
        if (doc != null && doc.getTramite() != null) {
            doc.setMarcado(!doc.isMarcado());
            documentoRepository.save(doc);

            // Redirige al trámite manteniendo el ancla
            if (anchor != null && !anchor.isEmpty()) {
                return "redirect:/tramite/" + doc.getTramite().getId() + "#" + anchor;
            }
            return "redirect:/tramite/" + doc.getTramite().getId();
        }
        return "redirect:/";
    }

    @GetMapping("/documento/marcar/{id}")
    public String capturarErrorGet(@PathVariable Long id) {
        return "redirect:/tramite/" + id;
    }

    @PostMapping("/comunidad/publicar")
    public String publicarConsejo(@ModelAttribute Consejo consejo, @RequestParam(name = "tramiteId", required = false) Long tramiteId) {
        if (tramiteId != null) {
            Tramite tramite = tramiteRepository.findById(tramiteId).orElse(null);
            consejo.setTramite(tramite);
        }
        if (consejo.getContenido() != null && !consejo.getContenido().trim().isEmpty()) {
            consejoRepository.save(consejo);
        }
        return "redirect:/comunidad";
    }

    @GetMapping("/sedes/buscar")
    public String buscarSedes(@RequestParam(name = "q", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            model.addAttribute("sedes", sedeRepository.findByNombreContainingIgnoreCase(query));
        } else {
            model.addAttribute("sedes", sedeRepository.findAll());
        }
        model.addAttribute("query", query);
        return "sedes";
    }

    @GetMapping("/comunidad")
    public String comunidad(Model model) {
        model.addAttribute("consejos", consejoRepository.findAll());
        model.addAttribute("nuevoConsejo", new Consejo());
        model.addAttribute("todosLosTramites", tramiteRepository.findAll());
        return "comunidad";
    }
    @GetMapping("/sedes")
    public String verTodasLasSedes(Model model) {
        model.addAttribute("sedes", sedeRepository.findAll());
        return "sedes";
    }
}