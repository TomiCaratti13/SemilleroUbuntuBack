package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.services.cargaImagenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/imagen")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class cargaImagenController {

    private final cargaImagenService cargaImagenService;
    @PutMapping("/altaOBaja")
    public ResponseEntity<?> darDeAltaOBaja(@RequestParam("imagenId") Long imagenId) throws ExceptionCreados {

            return cargaImagenService.darDeAltaODeBajaImagen(imagenId);

    }

}
