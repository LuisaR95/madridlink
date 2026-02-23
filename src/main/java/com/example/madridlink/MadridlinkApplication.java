package com.example.madridlink;

import com.example.madridlink.model.Consejo;
import com.example.madridlink.model.Tramite;
import com.example.madridlink.model.Documento;
import com.example.madridlink.model.Sede;
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
            // --- 1. CARGA DE TRÁMITES ---
            if (tramiteRepo.count() == 0) {
                Tramite nie = new Tramite();
                nie.setTitulo("NIE Inicial");
                nie.setDescripcion("Solicitud de residencia para ciudadanos extranjeros.");
                nie.setPlazo("90 días desde la entrada en España");
                nie.setUbicacion("Oficina de Extranjería (C/ Pradillo o Av. Poblados)");
                nie.setUrlCita("https://sede.administracionespublicas.gob.es/icpplus/index.html");
                nie.setCompletado(false);
                tramiteRepo.save(nie);

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

                System.out.println("✅ DATOS SEMILLA CREADOS");
            }

            // --- 2. CARGA DE SEDES ---
            if (sedeRepo.count() == 0) {
                Sede s1 = new Sede();
                s1.setNombre("Oficina de Extranjería - Calle Pradillo");
                s1.setDireccion("Calle de Pradillo, 40, 28002 Madrid");
                s1.setHorario("Lunes a Viernes de 09:00 a 14:00");
                s1.setTelefono("912 72 95 00");
                s1.setLatitud(40.447551);
                s1.setLongitud(-3.674442);
                sedeRepo.save(s1);

                Sede s2 = new Sede();
                s2.setNombre("Comisaría de Policía - Manuel Luna");
                s2.setDireccion("Calle de Manuel Luna, 29, 28020 Madrid");
                s2.setHorario("Lunes a Viernes de 09:00 a 14:30 y 16:00 a 21:00");
                s2.setTelefono("915 82 29 00");
                s2.setLatitud(40.457193);
                s2.setLongitud(-3.703274);
                sedeRepo.save(s2);

                Sede s3 = new Sede();
                s3.setNombre("Oficina de Extranjería - Aluche");
                s3.setDireccion("Avenida de los Poblados, s/n, 28044 Madrid");
                s3.setHorario("Lunes a Viernes de 09:00 a 14:00");
                s3.setTelefono("913 22 85 00");
                s3.setLatitud(40.375317);
                s3.setLongitud(-3.755057);
                sedeRepo.save(s3);

                System.out.println("✅ SEDES CARGADAS");
            }

            // --- 3. CARGA DE CONSEJOS (AQUÍ ESTABA EL ERROR) ---
            if (consejoRepo.count() == 0) {
                Consejo c1 = new Consejo();
                c1.setAutor("Carlos R.");
                c1.setCategoria("Sede Aluche");
                c1.setContenido("Fui ayer a por el NIE. Si llevas la tasa pagada desde el banco te ahorras mucha cola. ¡Ánimo!");
                consejoRepo.save(c1);

                Consejo c2 = new Consejo();
                c2.setAutor("Elena M.");
                c2.setCategoria("Empadronamiento");
                c2.setContenido("En el Ayuntamiento de Chamberí están dando citas bastante rápido para el padrón.");
                consejoRepo.save(c2);

                System.out.println("✅ CONSEJOS CARGADOS");
            }
        }; // Cierre del return args
    } // Cierre del Bean initData
} // Cierre de la clase