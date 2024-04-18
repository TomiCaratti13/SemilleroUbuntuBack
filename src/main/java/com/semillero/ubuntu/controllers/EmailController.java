package com.semillero.ubuntu.controllers;


import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.repositories.MicroEmprendimientoRepository;
import com.semillero.ubuntu.services.EmailService;
import com.semillero.ubuntu.services.MicroEmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    MicroEmprendimientoRepository microEmprendimientoRepository;

    @Autowired
    MicroEmprendimientoService microEmprendimientoService;


    @Scheduled(cron = "0 0 0 * * MON")
    @PostMapping("/enviar")
    public ResponseEntity<?> EnviarEmail() {


        try {
            emailService.enviarEmailSemanal();

            return ResponseEntity.noContent().build();
        }catch(Exception e){


            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

}
