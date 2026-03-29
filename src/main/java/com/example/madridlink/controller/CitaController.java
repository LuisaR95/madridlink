package com.example.madridlink.controller;

import com.example.madridlink.model.Cita;
import com.example.madridlink.model.Usuario;
import com.example.madridlink.repository.CitaRepository;
import com.example.madridlink.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/mis-citas")
    public String mostrarCalendario() {
        return "calendario";
    }


    @PostMapping("/citas/guardar")
    @ResponseBody
    public String guardarCita(@RequestParam String tramite,
                              @RequestParam String fechaHora,
                              Authentication auth) {


        Usuario usuarioActual = usuarioRepository.findByEmail(auth.getName()).orElse(null);

        if (usuarioActual == null) {
            return "Error: Usuario no autenticado";
        }


        LocalDateTime fecha = LocalDateTime.parse(fechaHora);


        Cita nuevaCita = new Cita(tramite, fecha, usuarioActual);
        citaRepository.save(nuevaCita);

        return "OK";
    }


    @GetMapping("/api/citas")
    @ResponseBody
    public List<EventoDTO> obtenerCitas() {
        return citaRepository.findAll().stream().map(cita -> {
            return new EventoDTO(
                    cita.getTramite(),
                    cita.getFechaHora().toString()
            );
        }).collect(Collectors.toList());
    }


    public static class EventoDTO {
        public String title;
        public String start;
        public EventoDTO(String title, String start) {
            this.title = title;
            this.start = start;
        }
    }
}