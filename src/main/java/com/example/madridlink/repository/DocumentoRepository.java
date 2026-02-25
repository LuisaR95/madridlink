    package com.example.madridlink.repository;

    import com.example.madridlink.model.Documento;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    }
