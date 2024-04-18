package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.dtos.CalculoDto;
import com.semillero.ubuntu.entities.Inversion;
import com.semillero.ubuntu.services.InversionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/mi_inversion")
public class InversionController {
    @Autowired
    private InversionService inversionService;

    @PostMapping("/crearInversion")
    public ResponseEntity<?> crearinversion(@RequestBody CalculoDto calculoDto,

                                            @RequestParam Long id_user,
                                            @RequestParam Long id_micro,
                                            @RequestParam Long id_riesgo) {
        try {
            Inversion inversion = inversionService.crearInversion(calculoDto,id_riesgo, id_user, id_micro);

            return ResponseEntity.status(HttpStatus.CREATED).body(inversion);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }

    }

}


