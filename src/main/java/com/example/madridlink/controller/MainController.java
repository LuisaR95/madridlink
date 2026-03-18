package com.example.madridlink.controller;

import com.example.madridlink.model.Documento;
import com.example.madridlink.model.Tramite;
import com.example.madridlink.model.Usuario;
import com.example.madridlink.model.Sede; // Asegúrate de importar tu modelo Sede
import com.example.madridlink.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired private TramiteRepository tramiteRepository;
    @Autowired private DocumentoRepository documentoRepository;
    @Autowired private SedeRepository sedeRepository;
    @Autowired private DocumentoUsuarioRepository documentoUsuarioRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        List<Tramite> tramites = tramiteRepository.findAll();
        model.addAttribute("allTramites", tramites);

        // --- NUEVO: Enviamos las sedes para el bloque de "Sedes Recomendadas" ---
        // Tomamos las primeras 2 o 3 para que no sature el diseño
        List<Sede> sedesInicio = sedeRepository.findAll().stream().limit(2).collect(Collectors.toList());
        model.addAttribute("sedesInicio", sedesInicio);

        int sumaProgresos = 0;
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElse(null);
            if (usuario != null) {
                for (Tramite t : tramites) {
                    List<Documento> docs = t.getDocumentos();
                    if (docs != null && !docs.isEmpty()) {
                        long marcados = docs.stream()
                                .filter(d -> documentoUsuarioRepository.findByUsuarioAndDocumento(usuario, d).isPresent())
                                .count();
                        int porcentaje = (int) ((marcados * 100) / docs.size());
                        t.setProgreso(porcentaje);
                        sumaProgresos += porcentaje;
                    }
                }
            }
        }

        int progresoGlobal = (tramites.size() > 0) ? (sumaProgresos / tramites.size()) : 0;
        model.addAttribute("tramites", tramites);
        model.addAttribute("progresoGlobal", progresoGlobal);
        return "index";
    }

    @GetMapping("/tramite/{id}")
    public String verDetalles(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("allTramites", tramiteRepository.findAll());
        Tramite tramite = tramiteRepository.findById(id).orElse(null);
        if (tramite == null) return "redirect:/";

        List<Documento> docs = tramite.getDocumentos();
        if (principal != null && docs != null) {
            Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElse(null);
            if (usuario != null) {
                docs.forEach(d -> d.setMarcado(documentoUsuarioRepository.findByUsuarioAndDocumento(usuario, d).isPresent()));
            }
        } else if (docs != null) {
            docs.forEach(d -> d.setMarcado(false));
        }

        model.addAttribute("tramite", tramite);
        model.addAttribute("documentos", docs);
        return "detalle";
    }

    @GetMapping("/tramites")
    public String verTramitesGeneral(Model model, Principal principal) {
        List<Tramite> lista = tramiteRepository.findAll();
        if (lista.isEmpty()) return "redirect:/";
        return verDetalles(lista.get(0).getId(), model, principal);
    }

    @GetMapping("/sedes")
    public String verSedes(@RequestParam(name = "q", required = false) String query, Model model) {
        model.addAttribute("sedes", (query != null && !query.isEmpty())
                ? sedeRepository.findByNombreContainingIgnoreCase(query)
                : sedeRepository.findAll());
        return "sedes";
    }
}