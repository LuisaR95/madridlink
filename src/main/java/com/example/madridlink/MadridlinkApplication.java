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

            // ---  CARGA DE SEDES ---
            if (sedeRepo.count() == 0) {
                Sede s1 = new Sede();
                s1.setNombre("Oficina de Extranjería - Calle Pradillo");
                s1.setDireccion("Calle de Pradillo, 40, 28002 Madrid");
                s1.setHorario("Lunes a Viernes de 09:00 a 14:00");
                s1.setTelefono("912 72 95 00");
                s1.setLatitud(40.448937);
                s1.setLongitud(-3.671362);
                sedeRepo.save(s1);

                Sede s2 = new Sede();
                s2.setNombre("Comisaría de Policía - Manuel Luna");
                s2.setDireccion("Calle de Manuel Luna, 29, 28020 Madrid");
                s2.setHorario("Lunes a Viernes de 09:00 a 14:30 y 16:00 a 21:00");
                s2.setTelefono("915 82 29 00");
                s2.setLatitud(40.454669);
                s2.setLongitud(-3.699779);
                sedeRepo.save(s2);

                Sede s3 = new Sede();
                s3.setNombre("Oficina de Extranjería - Aluche");
                s3.setDireccion("Avenida de los Poblados, s/n, 28044 Madrid");
                s3.setHorario("Lunes a Viernes de 09:00 a 14:00");
                s3.setTelefono("913 22 85 00");
                s3.setLatitud(40.382830);
                s3.setLongitud(-3.757562);
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
        };
    }
}