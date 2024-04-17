package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.entities.Contacto;
import com.semillero.ubuntu.services.impl.ContactoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contacto")
@CrossOrigin(origins = "*")
public class ContactoController {

    @Autowired
    private ContactoServiceImpl service;

    @PostMapping("/{idMicroemprendiminto}")
    public ResponseEntity<?> save(@Valid @RequestBody Contacto contacto, BindingResult result, @PathVariable Long idMicroemprendiminto){
        System.out.println(contacto);
        if(result.hasErrors()){
            return validation(result);
        }
        return service.save(contacto,idMicroemprendiminto);
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return service.findAll();
    }
    @PutMapping("/gestionar/{id}")
    public ResponseEntity<?> gestionar(@PathVariable Long id){
        return service.gestionar(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return service.findById(id);
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
