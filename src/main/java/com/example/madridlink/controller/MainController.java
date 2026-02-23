package com.example.madridlink.controller;

import com.example.madridlink.model.Tramite;
import com.example.madridlink.model.Documento; // Importante añadir este
import com.example.madridlink.repository.SedeRepository;
import com.example.madridlink.repository.TramiteRepository;
import com.example.madridlink.repository.DocumentoRepository; // Añadido para limpieza
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; // Importante para el formulario
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private TramiteRepository tramiteRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tramites", tramiteRepository.findAll());
        return "index";
    }

    @GetMapping("/tramite/{id}")
    public String verDetalles(@PathVariable Long id, Model model) {
        Tramite tramite = tramiteRepository.findById(id).orElse(null);

        if (tramite == null) {
            return "redirect:/";
        }

        model.addAttribute("tramite", tramite);
        model.addAttribute("documentos", tramite.getDocumentos());

        return "detalle";
    }

    // --- NUEVO MÉTODO PARA LA INTERACTIVIDAD ---
    @PostMapping("/documento/marcar/{id}")
    public String marcarDocumento(@PathVariable Long id) {
        // 1. Buscamos el documento que el usuario pulsó
        Documento doc = documentoRepository.findById(id).orElse(null);

        if (doc != null) {
            // 2. Cambia el estado (si estaba en true pasa a false y viceversa)
            doc.setMarcado(!doc.isMarcado());

            // 3. Guarda el cambio en la base de datos
            documentoRepository.save(doc);

            // 4. Redirige a la página del trámite para ver el check actualizado
            return "redirect:/tramite/" + doc.getTramite().getId() + "#lista-documentos";
        }

        return "redirect:/";
    }

    @GetMapping("/sedes/buscar")
    public String buscarSedes(@RequestParam(name = "q", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            // Se llama al método del Repository
            model.addAttribute("sedes", sedeRepository.findByNombreContaining(query));
        } else {
            model.addAttribute("sedes", sedeRepository.findAll());
        }
        return "sedes"; // Nos devuelve a la misma página pero con los resultados filtrados
    }
}
