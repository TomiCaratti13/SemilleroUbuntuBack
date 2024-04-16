package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.dtos.Cant_Mic_RubroDTO;
import com.semillero.ubuntu.dtos.DtoRiesgo;
import com.semillero.ubuntu.entities.Riesgo;
import com.semillero.ubuntu.services.RiesgoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/riesgo")
public class RiesgoController {

    @Autowired
    private RiesgoService riesgoService;
    @PostMapping("/crearRiesgo")
    public ResponseEntity<?> crearRiesgo(@RequestBody Riesgo nuevo_riesgo) {

        try {
            Riesgo riesgo = riesgoService.crearRiesgo(nuevo_riesgo);

            return ResponseEntity.status(HttpStatus.CREATED).body(riesgo.getId());

        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/riego_calc")
    public ResponseEntity<List<DtoRiesgo>> listaNivel(){

        try {
            List<DtoRiesgo> resultado = riesgoService.listaNivel();
            return new ResponseEntity<>(resultado,HttpStatus.OK) ;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @GetMapping ("/calculo")
    public ResponseEntity<?> calculo(@RequestParam Long id, @RequestParam Long monto) {
        try {
            return riesgoService.calculo(id, monto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
