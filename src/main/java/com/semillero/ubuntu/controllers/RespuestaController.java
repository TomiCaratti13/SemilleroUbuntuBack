package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.services.impl.RespuestaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {
    @Autowired
    private RespuestaServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
