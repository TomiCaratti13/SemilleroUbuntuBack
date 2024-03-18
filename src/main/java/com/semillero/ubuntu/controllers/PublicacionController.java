package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.services.impl.PublicacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/publicacion")
public class PublicacionController {

    @Autowired
    private PublicacionServiceImpl service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

    @GetMapping("/activas")
    public ResponseEntity<?> activas() {
        return service.activas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Publicacion publicacion, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return service.save(publicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody Publicacion publicacion, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return service.update(publicacion, id);
    }

    @PutMapping("/visualizacion/{id}")
    public ResponseEntity<?> aumentarView(@PathVariable Long id) {
        return service.incrementarVisualizacion(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inhabilitar(@PathVariable Long id) {
        return service.deleted(id);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
