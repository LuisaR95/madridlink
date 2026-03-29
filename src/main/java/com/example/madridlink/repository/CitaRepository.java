package com.example.madridlink.repository;

import com.example.madridlink.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Busca citas que ocurran entre dos fechas (inicio y fin del día de mañana)
    @Query("SELECT c FROM Cita c WHERE c.fechaHora >= :inicio AND c.fechaHora <= :fin")
    List<Cita> findByFechaHoraBetween(
        @Param("inicio") LocalDateTime inicio, 
        @Param("fin") LocalDateTime fin
    );
}