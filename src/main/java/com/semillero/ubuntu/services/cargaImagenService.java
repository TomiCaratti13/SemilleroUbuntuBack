package com.semillero.ubuntu.services;

import com.semillero.ubuntu.exceptions.ExceptionCreados;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface cargaImagenService {
    ResponseEntity<?> cargarImagen(@RequestParam("imagen") MultipartFile imagen) throws ExceptionCreados;

    ResponseEntity<?> modificarImagen(@RequestParam("imagen") MultipartFile imagen) throws ExceptionCreados;

    ResponseEntity<?> darDeAltaODeBajaImagen() throws ExceptionCreados;
}