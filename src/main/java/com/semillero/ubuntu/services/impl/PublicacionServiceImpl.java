package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.repositories.PublicacionRespository;
import com.semillero.ubuntu.services.PublicacionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.Optional;


@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionRespository repository;

    @Transactional
    @Override
    public ResponseEntity<?> save(Publicacion publicacion) {
        publicacion.setFechaCreacion(new Date());
        return ResponseEntity.status(201).body(repository.save(publicacion));
    }

    @Transactional
    @Override
    public ResponseEntity<?> update(Publicacion publicacion, Long id) {
        Optional<Publicacion> o = repository.findById(id);
        if (o.isPresent()) {
            Publicacion publicacionDb = o.get();
            publicacionDb.setTitulo(publicacion.getTitulo());
            publicacionDb.setDescripcion(publicacion.getDescripcion());
            return ResponseEntity.status(201).body(repository.save(publicacionDb));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<Publicacion> o = repository.findById(id);
        if (o.isPresent()) {
            Publicacion publicacion = o.get();
            return ResponseEntity.ok(publicacion);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<?> activas() {
        return ResponseEntity.ok(repository.findByDeletedFalse());
    }

    @Transactional
    @Override
    public ResponseEntity<?> incrementarVisualizacion(Long id) {
        Optional<Publicacion> o = repository.findById(id);
        if (o.isPresent()) {
            Publicacion publicacion = o.get();
            if (publicacion.getVisualizaciones() != null) {
                publicacion.setVisualizaciones(publicacion.getVisualizaciones() + 1);
            } else {
                publicacion.setVisualizaciones(1);
            }
            repository.save(publicacion);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleted(Long id) {
        Optional<Publicacion> o = repository.findById(id);
        if (o.isPresent()) {
            Publicacion publicacion = o.get();
            publicacion.setDeleted(true);
            repository.save(publicacion);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
