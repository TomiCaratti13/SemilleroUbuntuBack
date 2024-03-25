package com.semillero.ubuntu.services;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmailService {


    public ResponseEntity<?> enviarEmailSemanal();

    public List<String> recopilarNuevosMicroemprendimientos();

}
