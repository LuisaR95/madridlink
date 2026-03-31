package com.example.madridlink.service;

import com.example.madridlink.model.Cita;
import com.example.madridlink.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class CitaTask {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private EmailService emailService; // Esto ya no debería salir en rojo si están en el mismo paquete

    /**
     * Tarea programada que se ejecuta cada mañana a las 9:00 AM
     */
    @Scheduled(cron = "0 * * * * ?")
    public void enviarRecordatorios() {

        LocalDate mañana = LocalDate.now().plusDays(1);
        LocalDateTime inicioMañana = mañana.atStartOfDay();
        LocalDateTime finMañana = mañana.atTime(LocalTime.MAX);


        List<Cita> citasDeMañana = citaRepository.findByFechaHoraBetween(inicioMañana, finMañana);


        for (Cita cita : citasDeMañana) {
            String destinatario = cita.getUsuario().getEmail();
            String asunto = "🔔 Recordatorio: Cita MadriLink - " + cita.getTramite();
            String cuerpo = "Hola " + cita.getUsuario().getEmail() + ",\n\n" +
                            "Te recordamos que tienes una cita para: " + cita.getTramite() + "\n" +
                            "Fecha: " + mañana + "\n" +
                            "Hora: " + cita.getFechaHora().toLocalTime() + "\n\n" +
                            "¡Que tengas un buen día!";

            emailService.enviarCorreo(destinatario, asunto, cuerpo);
        }
    }
}