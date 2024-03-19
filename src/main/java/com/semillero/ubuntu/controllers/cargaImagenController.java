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
public class cargaImagenController {

    private final cargaImagenService cargaImagenService;
/*
    @PostMapping("/cargar/{publicacionId}")
    public ResponseEntity<?> cargarImagen(@RequestParam("imagenes") List<MultipartFile> imagenes, @PathVariable("publicacionId") Long publicacionId) {
        try {
            return cargaImagenService.cargarImagenPublicacion(imagenes, publicacionId);
        } catch (ExceptionCreados e) {
            // Manejo de la excepción aquí
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar las imágenes");
        }
    }


    @PutMapping("/modificar")
    public ResponseEntity<?> modificarImagen(@RequestParam("imagenes") List<MultipartFile> nuevasImagenes, @RequestParam("publicacionId") Long publicacionId) throws ExceptionCreados {

            return cargaImagenService.modificarImagenPublicacion(nuevasImagenes, publicacionId);

    }*/

    @PutMapping("/altaOBaja")
    public ResponseEntity<?> darDeAltaOBaja(@RequestParam("imagenId") Long imagenId) throws ExceptionCreados {

            return cargaImagenService.darDeAltaODeBajaImagen(imagenId);

    }

}
