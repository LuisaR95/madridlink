package com.example.madridlink.controller;

import com.example.madridlink.model.Usuario;
import com.example.madridlink.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }


    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }


    @PostMapping("/registro")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {


        if (result.hasErrors()) {
            return "registro"; // Si hay errores, recarga la página de registro y los muestra
        }

        // Si no hay errores, procedemos con la seguridad y el guardado
        // Encriptar la contraseña para que Spring Security pueda leerla
        String passwordSegura = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordSegura);


        usuarioRepository.save(usuario);


        return "redirect:/login?success";
    }
}