package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Publicacion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PublicacionService {
    Publicacion save(Publicacion publicacion);
    Optional<Publicacion> findById(Long id);
    List<Publicacion> findAll();
    List<Publicacion> activas();
    void incrementarVisualizacion(Long id);
    void deleted(Long id);

}
