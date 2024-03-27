package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.repositories.MicroEmprendimientoRepository;
import com.semillero.ubuntu.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MicroEmprendimientoRepository microEmprendimientoRepository;

    List<MicroEmprendimiento> nuevosMicroemprendimientos;

    @Transactional
    @Override
        public ResponseEntity<?> enviarEmailSemanal() {


            SimpleMailMessage message = new SimpleMailMessage();

            String mail= "Estimado usuario,\n\nEs un placer para nosotros informarle sobre los nuevos microemprendimientos. " +
                         "Esta semana hemos agregado los siguientes microemprendimientos:\n" +
                    recopilarNuevosMicroemprendimientos() +
                    "\n\nLo esperamos en nuestro sitio web si desea obtener más información. \n\nMuchas gracias,\n" +
                    "EQUIPO UBUNTU";

            message.setFrom("semilleroubuntu@gmail.com");
            message.setTo("semilleroubuntu@gmail.com");
            message.setText(mail);
            message.setSubject("prueba");
            mailSender.send(message);

            return new ResponseEntity<>(true, HttpStatus.OK);
        }

    public List<String> recopilarNuevosMicroemprendimientos() {
        // Calcular la fecha de inicio de la semana actual (lunes)
        LocalDate inicioSemana = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        // Calcular la fecha de fin de la semana actual (domingo)
        LocalDate finSemana = inicioSemana.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        // Recopilar los microemprendimientos agregados durante la semana actual

        List<MicroEmprendimiento> nuevosMicroEmprendimientos= microEmprendimientoRepository.buscarPorRangoDeFecha(inicioSemana, finSemana);

        // Procesar los nuevos microemprendimientos (por ejemplo, enviar por correo electrónico, almacenar en otro lugar, etc.)
        // Ejemplo: enviarCorreoElectronico(nuevosMicroemprendimientos);

        List<String> nombres = new ArrayList<>();

        // Obtener los nombres de los microemprendimientos y almacenarlos en la lista nombres
        for (MicroEmprendimiento microemprendimiento : nuevosMicroEmprendimientos) {
            nombres.add(microemprendimiento.getNombre());
        }


        return nombres;
    }

}
