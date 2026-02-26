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

    @OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Documento> documentos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL)
    private List<Consejo> consejos;

    public Tramite() {
    }


    public int getProgresoCalculado() {
        if (documentos == null || documentos.isEmpty()) {
            return completado ? 100 : 0;
        }
        long marcados = documentos.stream().filter(Documento::isMarcado).count();
        return (int) ((marcados * 100) / documentos.size());
    }

    // --- TUS GETTERS Y SETTERS ORIGINALES ---
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

    public Sede getSede() { return sede; }
    public void setSede(Sede sede) { this.sede = sede; }

    public List<Consejo> getConsejos() { return consejos; }
    public void setConsejos(List<Consejo> consejos) { this.consejos = consejos; }
}