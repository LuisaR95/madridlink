package com.example.madridlink.controller;

import com.example.madridlink.model.Usuario;
import com.example.madridlink.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // RUTA DE LOGIN
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Debe existir src/main/resources/templates/login.html
    }

    // 3. RUTA DE REGISTRO (Formulario)
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro"; // Debe existir src/main/resources/templates/registro.html
    }

    // 4. PROCESAR REGISTRO
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        // Encriptar la contraseña para que Spring Security pueda leerla
        String passwordSegura = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordSegura);

        // Guardar en la base de datos (DBeaver)
        usuarioRepository.save(usuario);

        // Redirigir al login con un mensaje de éxito
        return "redirect:/login?success";
    }
}