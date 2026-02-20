package com.example.madridlink;

import com.example.madridlink.model.Tramite;
import com.example.madridlink.repository.TramiteRepository;
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
    CommandLineRunner initData(TramiteRepository repo) {
        return args -> {
            // Si no hay trámites, los creamos nosotros por código
            if (repo.count() == 0) {
                Tramite t1 = new Tramite();
                t1.setTitulo("NIE Inicial");
                t1.setDescripcion("Solicitud de residencia en Madrid");
                t1.setCompletado(false);

                Tramite t2 = new Tramite();
                t2.setTitulo("Empadronamiento");
                t2.setDescripcion("Registro en el ayuntamiento");
                t2.setCompletado(true);

                repo.save(t1);
                repo.save(t2);
                System.out.println("✅ DATOS DE PRUEBA CREADOS CON ÉXITO");
            }
        };
    }
}