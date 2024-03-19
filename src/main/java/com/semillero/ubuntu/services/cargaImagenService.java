package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface cargaImagenService {
    ResponseEntity<?> cargarImagenPublicacion(Long id,@RequestParam("imagenes") List<MultipartFile> imagenes) throws ExceptionCreados;

    ResponseEntity<?> modificarImagenPublicacion(@RequestParam("imagenes") List<MultipartFile> nuevasImagenes,@RequestParam() Publicacion publicacion) throws ExceptionCreados;

    ResponseEntity<?> darDeAltaODeBajaImagen(Long imagenId) throws ExceptionCreados;
}