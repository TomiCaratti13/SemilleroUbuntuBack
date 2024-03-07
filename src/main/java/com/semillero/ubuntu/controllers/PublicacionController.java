package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.services.impl.PublicacionServiceImpl;
import jakarta.validation.Valid;
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
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/activas")
    public ResponseEntity<?> activas() {
        return ResponseEntity.ok(service.activas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Publicacion> o = service.findById(id);
        if (o.isPresent()) {
            Publicacion publicacion = o.get();
            return ResponseEntity.ok(publicacion);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Publicacion publicacion, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(201).body(service.save(publicacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@Valid @RequestBody Publicacion publicacion, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Publicacion> o = service.findById(id);
        if (o.isPresent()) {
            Publicacion publicacionDb = o.get();
            publicacionDb.setTitulo(publicacion.getTitulo());
            publicacionDb.setDescripcion(publicacion.getDescripcion());
            return ResponseEntity.status(201).body(service.save(publicacionDb));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/visualizacion/{id}")
    public ResponseEntity<?> aumentarView(@PathVariable Long id){
        service.incrementarVisualizacion(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inhabilitar(@PathVariable Long id){
        service.deleted(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
