package com.example.madridlink;

import com.example.madridlink.repository.ConsejoRepository;
import com.example.madridlink.repository.SedeRepository;
import com.example.madridlink.repository.TramiteRepository;
import com.example.madridlink.repository.DocumentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling; // NUEVO: Para el correo programado

@SpringBootApplication
@EnableScheduling // NUEVO: Activa el reloj interno de Spring para mandar correos automáticamente
public class MadridlinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MadridlinkApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(TramiteRepository tramiteRepo, DocumentoRepository docRepo, SedeRepository sedeRepo, ConsejoRepository consejoRepo) {
        return args -> {

            System.out.println("🚀 Servidor MadriLink iniciado correctamente.");
            System.out.println("📦 Los datos iniciales se están cargando desde import.sql...");
        };
    }
}