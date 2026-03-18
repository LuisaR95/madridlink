package com.example.madridlink.controller;

import com.example.madridlink.model.Documento;
import com.example.madridlink.model.DocumentoUsuario;
import com.example.madridlink.repository.DocumentoRepository;
import com.example.madridlink.repository.DocumentoUsuarioRepository;
import com.example.madridlink.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.madridlink.model.Usuario;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ProgresoController {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private DocumentoUsuarioRepository documentoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/documento/marcar/{id}")
    public String marcarDocumento(@PathVariable Long id,
                                  @RequestParam String anchor,
                                  Principal principal) {

        // 1. Si el usuario no está logueado, lo mandamos al login
        if (principal == null) {
            return "redirect:/login";
        }

        // 2. Buscamos quién es el usuario logueado por su email
        String email = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        Documento doc = documentoRepository.findById(id).orElse(null);

        if (usuario != null && doc != null) {
            // 3. Verificamos si ya existe el registro (si ya estaba marcado)
            Optional<DocumentoUsuario> existente = documentoUsuarioRepository.findByUsuarioAndDocumento(usuario, doc);

            if (existente.isPresent()) {
                // Si existe, lo borramos (desmarcar)
                documentoUsuarioRepository.delete(existente.get());
            } else {
                // Si no existe, lo creamos (marcar)
                DocumentoUsuario nuevoProgreso = new DocumentoUsuario(usuario, doc, true);
                documentoUsuarioRepository.save(nuevoProgreso);
            }

            // Usamos el anchor para volver a la misma posición del scroll
            return "redirect:/tramite/" + doc.getTramite().getId() + "#" + anchor;
        }

        return "redirect:/";
    }
}