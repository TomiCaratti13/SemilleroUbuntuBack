package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Transactional
    @Override
        public ResponseEntity<?> enviarEmailSemanal() {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("semilleroubuntu@gmail.com");
            message.setTo("semilleroubuntu@gmail.com");
            message.setText("hola");
            message.setSubject("prueba");
            mailSender.send(message);

            return new ResponseEntity<>(true, HttpStatus.OK);
        }


}
