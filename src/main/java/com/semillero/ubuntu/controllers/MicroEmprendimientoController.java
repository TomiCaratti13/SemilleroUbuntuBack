package com.semillero.ubuntu.controllers;


import com.semillero.ubuntu.dtos.MicroEmprendimientoRequest;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.services.MicroEmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/microEmprendimiento")
public class MicroEmprendimientoController {

    @Autowired
    MicroEmprendimientoService microEmprendimientoService;

    @PostMapping("/crear")
    public ResponseEntity<?> CrearMicroEmpendimiento(@RequestBody MicroEmprendimiento microEmprendimiento)throws ExceptionCreados {

        try {
            microEmprendimientoService.CrearMicroEmprendimiento(microEmprendimiento);

            return ResponseEntity.noContent().build();
        }catch(Exception e){


            return ResponseEntity.notFound().build();

        }



    }

    @PostMapping("editar/{id}")
    public ResponseEntity<?> EditarMicroEmpendimiento(@PathVariable String id, @RequestBody MicroEmprendimientoRequest microEmprendimientoRequest)throws ExceptionCreados{

        try {
            microEmprendimientoService.EditarMicroEmprendimiento(id, microEmprendimientoRequest);

            return ResponseEntity.noContent().build();

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/eliminar/{id}")
    public ResponseEntity<?> EliminarMicroEmprendimiento(@PathVariable String id) throws ExceptionCreados{

        try {
            microEmprendimientoService.Eliminar(id);

            return ResponseEntity.noContent().build();

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/Listar")
    public ResponseEntity<?> ListarMicroEmprendimientos()throws ExceptionCreados{

        try {

            return ResponseEntity.ok(microEmprendimientoService.listarMicroEmprendimientos());

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }

    }
}
