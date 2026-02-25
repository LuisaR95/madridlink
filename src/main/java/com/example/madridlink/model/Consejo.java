package com.example.madridlink.model;

import jakarta.persistence.*;

@Entity
public class Consejo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;
    private String categoria;

    @Column(length = 500)
    private String contenido;

    // 🔗 CONEXIÓN: Muchos consejos pertenecen a un trámite
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tramite_id")
    private Tramite tramite;

    public Consejo() {
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    // 📝 ¡IMPORTANTE! Getters y Setters para la relación
    public Tramite getTramite() { return tramite; }
    public void setTramite(Tramite tramite) { this.tramite = tramite; }
}