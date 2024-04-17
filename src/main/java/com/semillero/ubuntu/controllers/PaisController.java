package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.entities.Pais;
import com.semillero.ubuntu.entities.Provincia;
import com.semillero.ubuntu.exceptions.ResourceNotFoundException;
import com.semillero.ubuntu.services.PaisService;
import com.semillero.ubuntu.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
// http://localhost:8080/api/ubuntu
@RequestMapping("/api/ubuntu")
@CrossOrigin(origins = "*")
public class PaisController {

    @Autowired
    private PaisService paisService;
    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> obtenerPaises() {
        List<Pais> paises = paisService.listarPaises();
        return ResponseEntity.ok(paises);
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> obtenerPaisPorId(@PathVariable Integer id) {
        Pais pais = this.paisService.buscarPaisPorId(id);
        if(pais != null)
            return ResponseEntity.ok(pais);
        else
            throw new ResourceNotFoundException("No se encontro el pais con ID: " + id);
    }

    @GetMapping("/paises/{id}/provincias")
    public ResponseEntity<List<Provincia>> obtenerProvinciasPorPais(@PathVariable Integer id) {
        List<Provincia> provincias =  paisService.listarProvinciasPorPais(id);
        return ResponseEntity.ok(provincias);
    }

    @PostMapping("/paises")
    public void agregarPais(@RequestBody Pais pais) {
        List<Provincia> provincias =pais.getProvincias().stream().map(p -> provinciaService.guardarProvincia(p)).collect(Collectors.toList());
        pais.setProvincias(provincias);
        this.paisService.guardarPais(pais);
    }

    @PutMapping("/paises/{id}")
    public ResponseEntity<Pais> actualizarPais(@PathVariable Integer id, @RequestBody Pais paisNuevo) {
        Pais pais = this.paisService.buscarPaisPorId(id);
        if (pais == null)
            throw new ResourceNotFoundException("No se encontro el ID: " + id);
        pais.setNombre(paisNuevo.getNombre());
        this.paisService.guardarPais(pais);
        return ResponseEntity.ok(pais);
    }

    @DeleteMapping("/paises/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPais(@PathVariable Integer id){
        Pais pais = paisService.buscarPaisPorId(id);
        if (pais == null)
            throw new ResourceNotFoundException("No se encontro el ID: " + id);
        this.paisService.eliminarPaisPorId(pais.getId());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Pais eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
