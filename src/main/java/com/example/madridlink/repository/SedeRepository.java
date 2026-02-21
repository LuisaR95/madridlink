package com.example.madridlink.repository;

import com.example.madridlink.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long> {

    List<Sede> findByNombreContaining(String nombre);
}