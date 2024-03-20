package com.semillero.ubuntu.services;

import com.semillero.ubuntu.dtos.ImagenDTO;
import com.semillero.ubuntu.entities.Imagen;
import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface cargaImagenService {
    ResponseEntity<?> cargarImagenPublicacion(Long id,@RequestParam("imagenes") List<MultipartFile> imagenes) throws ExceptionCreados;

    ResponseEntity<?> modificarImagenPublicacion(Long id,@RequestParam("imagenes") List<MultipartFile> nuevasImagenes) throws ExceptionCreados;

    ResponseEntity<?> darDeAltaODeBajaImagen(Long imagenId) throws ExceptionCreados;
    List<ImagenDTO> convertirAImagenDTO(List<Imagen> imagenes);
}