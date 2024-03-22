package com.semillero.ubuntu.services;

import org.springframework.http.ResponseEntity;

public interface EmailService {


    public ResponseEntity<?> enviarEmailSemanal();
}
