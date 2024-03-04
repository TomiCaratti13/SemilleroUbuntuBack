package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.entities.Rubro;
import com.semillero.ubuntu.services.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rubro")
public class RubroController {

    @Autowired
    RubroService rubroService;

    @PostMapping("/crearRubro")
    public ResponseEntity<?> crear( @RequestBody Rubro rubro) {

        try {

            rubroService.cargarNuevoRubro(rubro);


            return ResponseEntity.noContent().build();

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
