package com.example.madridlink.repository;

import com.example.madridlink.model.Documento;
import com.example.madridlink.model.DocumentoUsuario;
import com.example.madridlink.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional; // ¡Faltaba este import!

@Repository
public interface DocumentoUsuarioRepository extends JpaRepository<DocumentoUsuario, Long> {

    // Para recuperar todo lo que un usuario específico ha marcado
    List<DocumentoUsuario> findByUsuario(Usuario usuario);

    // Busca si existe ya el registro para un usuario y un documento concreto
    // Ahora está DENTRO de las llaves y con los imports correctos
    Optional<DocumentoUsuario> findByUsuarioAndDocumento(Usuario usuario, Documento documento);
}