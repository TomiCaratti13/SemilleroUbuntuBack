package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.entities.Provincia;
import com.semillero.ubuntu.exceptions.ResourceNotFoundException;
import com.semillero.ubuntu.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/ubuntu")
@CrossOrigin(origins = "*")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/provincias")
    public ResponseEntity<List<Provincia>> obtenerProvincias() {
        List<Provincia> provincias = provinciaService.listarProvincias();
        return new ResponseEntity<>(provincias, HttpStatus.OK);
    }

    @GetMapping("/provincias/{id}")
    public ResponseEntity<Provincia> obtenerProvinciaPorId(@PathVariable Integer id) {
        Provincia provincia = this.provinciaService.buscarProvinciaPorId(id);
        if(provincia != null)
            return ResponseEntity.ok(provincia);
        else
            throw new ResourceNotFoundException("No se encontro la provincia con ID: " + id);
    }

    @PostMapping("/provincias")
    // estuctura JSON para Postman
    // {    "nombre":"Argentina",
    //      "pais":{"id":1}
    // }
    public void agregarProvincia(@RequestBody Provincia provincia) {
        this.provinciaService.guardarProvincia(provincia);
    }

    @PutMapping("/provincias/{id}")
    public ResponseEntity<Provincia> actualizarProvincia(@PathVariable Integer id, @RequestBody Provincia provinciaNueva) {
        Provincia provincia = this.provinciaService.buscarProvinciaPorId(id);
        if (provincia == null)
            throw new ResourceNotFoundException("No se encontro el ID: " + id);
        provincia.setNombre(provinciaNueva.getNombre());

        this.provinciaService.guardarProvincia(provincia);
        return ResponseEntity.ok(provincia);
    }

    @DeleteMapping("/provincias/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProvincia(@PathVariable Integer id){
        Provincia provincia = provinciaService.buscarProvinciaPorId(id);
        if (provincia == null)
            throw new ResourceNotFoundException("No se encontro el ID: " + id);
        this.provinciaService.eliminarProvinciaPorId(provincia.getId());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Provincia eliminada", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
