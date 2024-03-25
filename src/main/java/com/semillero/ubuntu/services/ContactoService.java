package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Contacto;
import org.springframework.http.ResponseEntity;

public interface ContactoService {
    ResponseEntity<?>save(Contacto contacto,Long idMicroemprendimiento);
    ResponseEntity<?>findAll();
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> gestionar(Long id);
    ResponseEntity<?> deleted(Long id);
}
