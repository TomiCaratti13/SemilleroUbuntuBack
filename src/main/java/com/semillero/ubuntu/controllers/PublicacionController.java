package com.semillero.ubuntu.controllers;


import com.semillero.ubuntu.dtos.Cant_Mic_RubroDTO;
import com.semillero.ubuntu.dtos.PublicacionDto;
import com.semillero.ubuntu.dtos.VisualizacionesDTO;
import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.services.impl.PublicacionServiceImpl;
import com.semillero.ubuntu.services.impl.cargaImagenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/publicacion")
@CrossOrigin(origins = "*")
public class PublicacionController {
    @Autowired
    private PublicacionServiceImpl service;
    @Autowired
    cargaImagenImpl serviceImagen;

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

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Publicacion publicacion, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(service.save(publicacion));
    }

    @PostMapping("/{id}/imagenes")
    public ResponseEntity<?> saveImagenes(@PathVariable("id") Long id, @RequestParam("imagenes") List<MultipartFile> imagenes) {
        ;
        return serviceImagen.cargarImagenPublicacion(id, imagenes);
    }


    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody PublicacionDto publicacion, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(service.update(publicacion, id));
    }

    @PutMapping("/editImagenes/{id}")
    public ResponseEntity<?> editImagenes(@RequestParam("imagenes") List<MultipartFile> imagenes, @PathVariable Long id) {
        return serviceImagen.modificarImagenPublicacion(id, imagenes);
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

    @GetMapping("/visualizaciones_publicaciones_total")
    public ResponseEntity<List<VisualizacionesDTO>> obtenerCantidadesPorRubro() {

        try {
            List<VisualizacionesDTO> totales_visualizacion = service.obtenerTotalVisualizaciones();
            return new ResponseEntity<>(totales_visualizacion, HttpStatus.OK);

        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}