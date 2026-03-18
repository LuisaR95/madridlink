package com.example.madridlink.repository;

import com.example.madridlink.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Este método es "mágico": Spring genera la consulta SQL solo por el nombre
    Optional<Usuario> findByEmail(String email);
}