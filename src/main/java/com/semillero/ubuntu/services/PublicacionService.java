package com.semillero.ubuntu.services;

import com.semillero.ubuntu.dtos.PublicacionDto;
import com.semillero.ubuntu.entities.Publicacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicacionService {
    ResponseEntity<?> save(Publicacion publicacion);
    ResponseEntity<?> update(PublicacionDto publicacion, Long id);
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findAll();
    ResponseEntity<?> activas();
    ResponseEntity<?> incrementarVisualizacion(Long id);
    ResponseEntity<?> deleted(Long id);

}
