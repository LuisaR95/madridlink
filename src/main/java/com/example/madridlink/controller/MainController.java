package com.example.madridlink.controller;

import com.example.madridlink.model.*;
import com.example.madridlink.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired private TramiteRepository tramiteRepository;
    @Autowired private DocumentoRepository documentoRepository;
    @Autowired private SedeRepository sedeRepository;
    @Autowired private DocumentoUsuarioRepository documentoUsuarioRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    // --- PÁGINA DE INICIO CON CÁLCULO DE PROGRESO SEGURO ---
    @GetMapping("/")
    public String index(Model model, Principal principal) {
        List<Tramite> tramites = tramiteRepository.findAll();
        if (tramites == null) tramites = new ArrayList<>();

        model.addAttribute("allTramites", tramites);

        // Sedes Recomendadas (Límite 2 para el index)
        List<Sede> sedesInicio = sedeRepository.findAll().stream().limit(2).collect(Collectors.toList());
        model.addAttribute("sedesInicio", sedesInicio);

        int sumaProgresos = 0;
        int contadorTramitesConDocs = 0;

        if (principal != null && !tramites.isEmpty()) {
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
                        contadorTramitesConDocs++;
                    } else {
                        t.setProgreso(0);
                    }
                }
            }
        }

        // Protección contra división por cero
        int progresoGlobal = (contadorTramitesConDocs > 0) ? (sumaProgresos / contadorTramitesConDocs) : 0;

        model.addAttribute("tramites", tramites);
        model.addAttribute("progresoGlobal", progresoGlobal);
        return "index";
    }

    // --- DETALLE DEL TRÁMITE ---
    @GetMapping("/tramite/{id}")
    public String verDetalles(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("allTramites", tramiteRepository.findAll());
        Tramite tramite = tramiteRepository.findById(id).orElse(null);

        if (tramite == null) return "redirect:/";

        List<Documento> docs = tramite.getDocumentos();
        if (docs == null) docs = new ArrayList<>();

        if (principal != null && !docs.isEmpty()) {
            Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElse(null);
            if (usuario != null) {
                docs.forEach(d -> d.setMarcado(documentoUsuarioRepository.findByUsuarioAndDocumento(usuario, d).isPresent()));
            }
        } else {
            docs.forEach(d -> d.setMarcado(false));
        }

        model.addAttribute("tramite", tramite);
        model.addAttribute("documentos", docs);
        return "detalle";
    }

    // --- MARCAR DOCUMENTO ---
    @PostMapping("/documento/marcar/{id}")
    public String marcarDocumento(@PathVariable Long id,
                                  @RequestParam(required = false) String anchor,
                                  Principal principal) {
        if (principal == null) return "redirect:/login";

        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElse(null);
        Documento doc = documentoRepository.findById(id).orElse(null);

        if (usuario != null && doc != null) {
            Optional<DocumentoUsuario> existente = documentoUsuarioRepository.findByUsuarioAndDocumento(usuario, doc);

            if (existente.isPresent()) {
                documentoUsuarioRepository.delete(existente.get());
            } else {
                DocumentoUsuario nuevo = new DocumentoUsuario();
                nuevo.setUsuario(usuario);
                nuevo.setDocumento(doc);
                nuevo.setCompletado(true);
                documentoUsuarioRepository.save(nuevo);
            }

            String fragmento = (anchor != null && !anchor.isEmpty()) ? "#" + anchor : "";
            return "redirect:/tramite/" + doc.getTramite().getId() + fragmento;
        }

        return "redirect:/";
    }

    // --- BUSCADOR DE SEDES (CORREGIDO PARA TU HTML) ---
    @GetMapping({"/sedes", "/sedes/buscar"})
    public String verSedes(@RequestParam(name = "q", required = false) String q, Model model) {
        List<Sede> resultados;
        if (q != null && !q.isEmpty()) {
            // Buscamos por el parámetro "q" que viene del input
            resultados = sedeRepository.findByNombreContainingIgnoreCase(q);
        } else {
            resultados = sedeRepository.findAll();
        }

        model.addAttribute("sedes", resultados);
        // Enviamos "query" para que th:value="${query}" lo reconozca y no de error
        model.addAttribute("query", q);

        return "sedes";
    }

    // --- LISTA GENERAL ---
    @GetMapping("/tramites")
    public String verTramitesGeneral(Model model, Principal principal) {
        List<Tramite> lista = tramiteRepository.findAll();
        if (lista.isEmpty()) return "redirect:/";
        return verDetalles(lista.get(0).getId(), model, principal);
    }
}