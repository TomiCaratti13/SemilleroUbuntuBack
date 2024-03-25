package com.semillero.ubuntu.controllers;


import com.semillero.ubuntu.entities.Inversiones;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inversiones")
public class InversionController {

    @GetMapping("/saveInversion")
    public ResponseEntity<?> saveInversion(@RequestBody Inversiones inversiones){
        try{

        }catch
    }


    }


}
