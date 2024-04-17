package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.entities.Rubro;
import com.semillero.ubuntu.services.impl.RubroServiceImpl;
import com.semillero.ubuntu.services.impl.cargaImagenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rubro")
@CrossOrigin(origins = "*")
public class RubroController {

    @Autowired
    RubroServiceImpl rubroService;
    @Autowired
    cargaImagenImpl serviceImagen;
    @PostMapping("/crearRubro")
    public ResponseEntity<?> crear( @RequestBody Rubro rubro) {

        try {

            rubroService.cargarNuevoRubro(rubro);


            return ResponseEntity.noContent().build();

        } catch (Exception e) {



            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/crearImagenRubro/{id}")
    public ResponseEntity<?> crearimagen(@PathVariable("id") Long id, @RequestParam("imagen") MultipartFile imagen) {
        try {
            return serviceImagen.cargarImagenRubro(id, imagen);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



        @GetMapping("/listarRubros")
        public ResponseEntity<?> listaRubros(){

            try {

                return ResponseEntity.ok(rubroService.listarRubros());

            } catch (Exception e) {


                return ResponseEntity.notFound().build();
            }
    }


}
