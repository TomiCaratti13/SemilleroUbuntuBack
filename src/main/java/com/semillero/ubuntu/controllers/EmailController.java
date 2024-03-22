package com.semillero.ubuntu.controllers;


import com.semillero.ubuntu.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    EmailService emailService;


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
