package com.example.madridlink.repository;

import com.example.madridlink.model.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Long> {
}