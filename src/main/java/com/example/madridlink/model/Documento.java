package com.example.madridlink.model;

import jakarta.persistence.*;

@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String tipo;      // Para "Original/Copia"
    private String costo;     // Para "9.84€" o "Gratis"
    private boolean obligatorio;
    private boolean marcado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tramite_id")
    private Tramite tramite;

    public Documento() {
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCosto() { return costo; }
    public void setCosto(String costo) { this.costo = costo; }

    public boolean isObligatorio() { return obligatorio; }
    public void setObligatorio(boolean obligatorio) { this.obligatorio = obligatorio; }

    public boolean isMarcado() { return marcado; }
    public void setMarcado(boolean marcado) { this.marcado = marcado; }

    public Tramite getTramite() { return tramite; }
    public void setTramite(Tramite tramite) { this.tramite = tramite; }
}