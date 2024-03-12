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

  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> CrearMicroEmpendimiento(@RequestBody MicroEmprendimiento microEmprendimiento) {

        try {
            microEmprendimientoService.CrearMicroEmprendimiento(microEmprendimiento);

            return ResponseEntity.noContent().build();
        }catch(Exception e){


            return ResponseEntity.notFound().build();

        }



    }

    //  @PreAuthorize("hasRole('ROLE_ADMIN')")

    @PostMapping("editar/{id}")
    public ResponseEntity<?> EditarMicroEmpendimiento(@PathVariable String id, @RequestBody MicroEmprendimientoRequest microEmprendimientoRequest){

        try {
            microEmprendimientoService.EditarMicroEmprendimiento(id, microEmprendimientoRequest);

            return ResponseEntity.noContent().build();

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }

    }

    //  @PreAuthorize("hasRole('ROLE_ADMIN')")

    @GetMapping("/eliminar/{id}")
    public ResponseEntity<?> EliminarMicroEmprendimiento(@PathVariable String id) {

        try {
            microEmprendimientoService.EliminarMicroEmprendimiento(id);

            return ResponseEntity.noContent().build();

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/listar")
    public ResponseEntity<?> ListarMicroEmprendimientos(){

        try {

            return ResponseEntity.ok(microEmprendimientoService.ListarMicroEmprendimientos());

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<?> BuscarPorNombre(@RequestBody String nombre){


        try {

            return ResponseEntity.ok(microEmprendimientoService.buscarPorNombre(nombre));

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }


    }

    @PostMapping("/ocultar/{id}")
    public ResponseEntity<?> OcultarMicroEmprendimiento(@PathVariable String id) {

        try {
            microEmprendimientoService.ocultarMicroEmprendimiento(id);

            return ResponseEntity.noContent().build();

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }


    }
}
