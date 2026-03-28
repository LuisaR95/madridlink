package com.example.madridlink.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Restricciones para el Email
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe introducir un formato de correo válido")
    @Column(unique = true, nullable = false)
    private String email;

    // Restricciones para la Contraseña
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!¡¿?]).*$",
            message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un símbolo"
    )
    @Column(nullable = false)
    private String password;

    // Relación con el progreso de documentos
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<DocumentoUsuario> documentosMarcados;

    // --- CONSTRUCTORES ---
    public Usuario() {}

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DocumentoUsuario> getDocumentosMarcados() {
        return documentosMarcados;
    }

    public void setDocumentosMarcados(List<DocumentoUsuario> documentosMarcados) {
        this.documentosMarcados = documentosMarcados;
    }
}