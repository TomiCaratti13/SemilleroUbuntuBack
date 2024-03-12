package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.repositories.PublicacionRespository;
import com.semillero.ubuntu.services.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionRespository repository;

    @Override
    public Publicacion save(Publicacion publicacion) {
        return repository.save(publicacion);
    }

    @Override
    public Optional<Publicacion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Publicacion> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Publicacion> activas() {
        return repository.findByDeletedTrue();
    }

    @Override
    public void incrementarVisualizacion(Long id) {
        Optional<Publicacion> o = repository.findById(id);
        if (o.isPresent()) {
            Publicacion publicacion = o.get();
            if (publicacion.getVisualizaciones() != null) {
                publicacion.setVisualizaciones(publicacion.getVisualizaciones() + 1);
            } else {
                publicacion.setVisualizaciones(1);
            }
            repository.save(publicacion);
        }
    }

    @Override
    public void deleted(Long id) {
        Optional<Publicacion> o = repository.findById(id);
        if (o.isPresent()) {
            Publicacion publicacion = o.get();
            publicacion.setDeleted(false);
            repository.save(publicacion);
        }
    }
}
