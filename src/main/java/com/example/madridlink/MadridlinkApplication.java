package com.example.madridlink;

import com.example.madridlink.repository.ConsejoRepository;
import com.example.madridlink.repository.SedeRepository;
import com.example.madridlink.repository.TramiteRepository;
import com.example.madridlink.repository.DocumentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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