package com.example.madridlink.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tramite; // Ejemplo: "Cita Huella", "Asilo", etc.

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // --- CONSTRUCTORES ---
    public Cita() {}

    public Cita(String tramite, LocalDateTime fechaHora, Usuario usuario) {
        this.tramite = tramite;
        this.fechaHora = fechaHora;
        this.usuario = usuario;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTramite() { return tramite; }
    public void setTramite(String tramite) { this.tramite = tramite; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}