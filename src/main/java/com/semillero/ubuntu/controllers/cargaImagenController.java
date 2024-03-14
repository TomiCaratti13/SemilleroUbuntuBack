package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.services.cargaImagenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/imagen")
@RequiredArgsConstructor
public class cargaImagenController {

    private final cargaImagenService cargaImagenService;

    @PostMapping("/cargar")
    public ResponseEntity<?> cargaImagen(@RequestParam("imagen")MultipartFile imagen) throws IOException, ExceptionCreados {
        ResponseEntity<?> respuesta = cargaImagenService.cargarImagen(imagen);
        return respuesta;
    }
    @PostMapping("/modificar")
    public ResponseEntity<?> modificarImagen(@RequestParam("imagen")MultipartFile imagen) throws IOException, ExceptionCreados {
        ResponseEntity<?> respuesta = cargaImagenService.modificarImagen(imagen);
        return respuesta;
    }
    @PostMapping("/altaOBaja")
    public ResponseEntity<?> darDeAltaOBaja() throws IOException, ExceptionCreados {
        ResponseEntity<?> respuesta = cargaImagenService.darDeAltaODeBajaImagen();
        return respuesta;
    }
}
