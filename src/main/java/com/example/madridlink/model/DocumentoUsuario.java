package com.example.madridlink.model;

import jakarta.persistence.*;

@Entity
@Table(name = "documento_usuario")
public class DocumentoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el Usuario (SIN el "com.tuproyecto")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; 

    // Relación con el Documento
    @ManyToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;

    private boolean completado;

    // --- CONSTRUCTORES ---
    public DocumentoUsuario() {}

    public DocumentoUsuario(Usuario usuario, Documento documento, boolean completado) {
        this.usuario = usuario;
        this.documento = documento;
        this.completado = completado;
    }

    // --- GETTERS Y SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}