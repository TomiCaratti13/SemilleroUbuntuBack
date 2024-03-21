package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Publicacion;

import org.springframework.stereotype.Service;

import org.springframework.http.ResponseEntity;

public interface PublicacionService {
    ResponseEntity<?> save(Publicacion publicacion);
    ResponseEntity<?> update(Publicacion publicacion,Long id);
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findAll();
    ResponseEntity<?> activas();
    ResponseEntity<?> incrementarVisualizacion(Long id);
    ResponseEntity<?> deleted(Long id);

}
