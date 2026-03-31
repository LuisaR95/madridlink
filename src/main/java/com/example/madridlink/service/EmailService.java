package com.example.madridlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Método para enviar un correo sencillo
     * @param to Correo del destinatario
     * @param subject Asunto del mensaje
     * @param body Contenido del mensaje
     */
    public void enviarCorreo(String to, String subject, String body) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom("luisa.rincond95@gmail.com");
            mensaje.setTo(to);
            mensaje.setSubject(subject);
            mensaje.setText(body);

            mailSender.send(mensaje);
            System.out.println("📧 Correo enviado con éxito a: " + to);
        } catch (Exception e) {
            System.err.println("❌ Error al enviar correo: " + e.getMessage());
        }
    }
}