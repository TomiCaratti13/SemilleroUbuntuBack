package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Publicacion;

import org.springframework.stereotype.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

@Service
public interface PublicacionService {
    ResponseEntity<?> save(List<MultipartFile>imagenes, Publicacion publicacion);
    ResponseEntity<?> update(List<MultipartFile>imagenes,Publicacion publicacion,Long id);
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findAll();
    ResponseEntity<?> activas();
    ResponseEntity<?> incrementarVisualizacion(Long id);
    ResponseEntity<?> deleted(Long id);

}
