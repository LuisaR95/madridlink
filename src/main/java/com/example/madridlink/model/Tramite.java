package com.example.madridlink.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tramites")
public class Tramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private boolean completado;
    private String plazo;
    private String ubicacion;
    private String urlCita;

    // 1. Relación con Documentos (YA LA TENÍAS)
    @OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL)
    private List<Documento> documentos;

    // 2. NUEVA: Relación con la Sede (Muchos trámites pertenecen a una Sede)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sede_id")
    private Sede sede;

    // 3. NUEVA: Relación con Consejos (Un trámite tiene muchos consejos)
    @OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL)
    private List<Consejo> consejos;

    public Tramite() {
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isCompletado() { return completado; }
    public void setCompletado(boolean completado) { this.completado = completado; }

    public List<Documento> getDocumentos() { return documentos; }
    public void setDocumentos(List<Documento> documentos) { this.documentos = documentos; }

    public String getPlazo() { return plazo; }
    public void setPlazo(String plazo) { this.plazo = plazo; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getUrlCita() { return urlCita; }
    public void setUrlCita(String urlCita) { this.urlCita = urlCita; }

    // GETTERS Y SETTERS DE LAS NUEVAS RELACIONES
    public Sede getSede() { return sede; }
    public void setSede(Sede sede) { this.sede = sede; }

    public List<Consejo> getConsejos() { return consejos; }
    public void setConsejos(List<Consejo> consejos) { this.consejos = consejos; }
}