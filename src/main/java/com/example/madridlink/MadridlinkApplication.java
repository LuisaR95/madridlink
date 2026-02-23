package com.example.madridlink;

import com.example.madridlink.model.Tramite;
import com.example.madridlink.repository.SedeRepository;
import com.example.madridlink.repository.TramiteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.madridlink.model.Documento;
import com.example.madridlink.repository.DocumentoRepository;
import com.example.madridlink.model.Sede;

@SpringBootApplication
public class MadridlinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MadridlinkApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(TramiteRepository tramiteRepo, DocumentoRepository docRepo, SedeRepository sedeRepo) {
        return args -> {
            if (tramiteRepo.count() == 0) {
                // 1. Creación del Trámite del NIE
                Tramite nie = new Tramite();
                nie.setTitulo("NIE Inicial");
                nie.setDescripcion("Solicitud de residencia para ciudadanos extranjeros.");
                nie.setPlazo("90 días desde la entrada en España");
                nie.setUbicacion("Oficina de Extranjería (C/ Pradillo o Av. Poblados)");
                nie.setUrlCita("https://sede.administracionespublicas.gob.es/icpplus/index.html");
                nie.setCompletado(false);
                tramiteRepo.save(nie);

                // 2. Creación documentos para el NIE
                Documento doc1 = new Documento();
                doc1.setNombre("Pasaporte Original");
                doc1.setMarcado(true);
                doc1.setTramite(nie);
                docRepo.save(doc1);

                Documento doc2 = new Documento();
                doc2.setNombre("Formulario EX-15");
                doc2.setMarcado(false);
                doc2.setTramite(nie);
                docRepo.save(doc2);

                // 3. Creación Trámite (Empadronamiento)
                Tramite padron = new Tramite();
                padron.setTitulo("Empadronamiento");
                padron.setDescripcion("Registro en el ayuntamiento de Madrid.");
                padron.setCompletado(true);
                tramiteRepo.save(padron);

                Documento doc3 = new Documento();
                doc3.setNombre("Contrato de Alquiler");
                doc3.setMarcado(true);
                doc3.setTramite(padron);
                docRepo.save(doc3);

                System.out.println("✅ DATOS SEMILLA (TRÁMITES Y DOCS) CREADOS");
            }

            if (sedeRepo.count() == 0) {
                // SEDE 1: Calle Pradillo
                Sede s1 = new Sede();
                s1.setNombre("Oficina de Extranjería - Calle Pradillo");
                s1.setDireccion("Calle de Pradillo, 40, 28002 Madrid");
                s1.setHorario("Lunes a Viernes de 09:00 a 14:00");
                s1.setTelefono("912 72 95 00");
                s1.setLatitud(40.447551);
                s1.setLongitud(-3.674442);
                sedeRepo.save(s1);

                // SEDE 2: Manuel Luna (DNI y NIE)
                Sede s2 = new Sede();
                s2.setNombre("Comisaría de Policía - Manuel Luna");
                s2.setDireccion("Calle de Manuel Luna, 29, 28020 Madrid");
                s2.setHorario("Lunes a Viernes de 09:00 a 14:30 y 16:00 a 21:00");
                s2.setTelefono("915 82 29 00");
                s2.setLatitud(40.457193);
                s2.setLongitud(-3.703274);
                sedeRepo.save(s2);

                // SEDE 3: Aluche (CIE y Asilo)
                Sede s3 = new Sede();
                s3.setNombre("Oficina de Extranjería - Aluche");
                s3.setDireccion("Avenida de los Poblados, s/n, 28044 Madrid");
                s3.setHorario("Lunes a Viernes de 09:00 a 14:00");
                s3.setTelefono("913 22 85 00");
                s3.setLatitud(40.375317);
                s3.setLongitud(-3.755057);
                sedeRepo.save(s3);

                System.out.println("✅ Sedes cargadas correctamente en la base de datos");
            }
        };
    }
}


