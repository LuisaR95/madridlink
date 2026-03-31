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
    private EmailService emailService;

    /**
     * Tarea programada: Se ejecuta cada minuto para facilitar las pruebas (0 * * * * ?)
     * En producción se cambiaría a "0 0 9 * * ?" (9:00 AM)
     */
    @Scheduled(cron = "0 * * * * ?")
    public void enviarRecordatorios() {
        // 1. Definimos el rango de "Mañana"
        LocalDate mañana = LocalDate.now().plusDays(1);
        LocalDateTime inicioMañana = mañana.atStartOfDay();
        LocalDateTime finMañana = mañana.atTime(LocalTime.MAX);

        System.out.println("🤖 ROBOT: Buscando citas para la fecha: " + mañana);

        // 2. Buscamos en la base de datos
        List<Cita> citasDeMañana = citaRepository.findByFechaHoraBetween(inicioMañana, finMañana);

        if (citasDeMañana.isEmpty()) {
            System.out.println("📭 ROBOT: No hay citas programadas para mañana.");
        } else {
            System.out.println("📬 ROBOT: Se han encontrado " + citasDeMañana.size() + " cita(s).");

            for (Cita cita : citasDeMañana) {
                try {
                    String destinatario = cita.getUsuario().getEmail();
                    String asunto = "🔔 Recordatorio: Cita MadriLink - " + cita.getTramite();

                    String cuerpo = "Hola " + destinatario + ",\n\n" +
                            "Te recordamos que tienes una cita para el trámite: " + cita.getTramite() + "\n" +
                            "Fecha: " + mañana + "\n" +
                            "Hora: " + cita.getFechaHora().toLocalTime() + "\n\n" +
                            "Sede: " + (cita.getTramite() != null ? "Consultar en la App" : "Sede Principal") + "\n\n" +
                            "¡Gracias por usar MadriLink!";

                    // 3. Enviamos el correo
                    emailService.enviarCorreo(destinatario, asunto, cuerpo);
                    System.out.println("✅ LOG: Recordatorio enviado correctamente a: " + destinatario);

                } catch (Exception e) {
                    System.err.println("❌ LOG: Error al procesar la cita ID " + cita.getId() + ": " + e.getMessage());
                }
            }
        }
    }
}