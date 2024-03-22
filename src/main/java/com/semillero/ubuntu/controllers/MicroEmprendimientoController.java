package com.semillero.ubuntu.controllers;


import com.semillero.ubuntu.dtos.MicroEmprendimientoRequest;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.services.MicroEmprendimientoService;
import com.semillero.ubuntu.services.impl.cargaImagenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/microEmprendimiento")
public class MicroEmprendimientoController {

    @Autowired
    cargaImagenImpl serviceImagen;

    @Autowired
    MicroEmprendimientoService microEmprendimientoService;

  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> CrearMicroEmpendimiento(@RequestBody MicroEmprendimiento microEmprendimiento) {

        try {
            microEmprendimientoService.CrearMicroEmprendimiento(microEmprendimiento);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();

        }
    }
    @PostMapping("/crearImagenes/{id}")
    public ResponseEntity<?> CrearMicroEmpendimientoImagenes(@PathVariable("id") Long id,@RequestParam("imagenes") List<MultipartFile> imagenes) {

        try {

            return serviceImagen.cargarImagenMicroemprendimiento(id,imagenes);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //  @PreAuthorize("hasRole('ROLE_ADMIN')")

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> EditarMicroEmpendimiento(@PathVariable Long id, @RequestBody MicroEmprendimientoRequest microEmprendimientoRequest){

        try {
            microEmprendimientoService.EditarMicroEmprendimiento(id, microEmprendimientoRequest);

            return ResponseEntity.noContent().build();

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }

    }
    @PutMapping("/editarImagenes/{id}")
    public ResponseEntity<?> EditarMicroEmpendimientoImagenes(@PathVariable Long id,@RequestParam("imagenes") List<MultipartFile> imagenes){

        try {
            return serviceImagen.modificarImagenMicroemprendimiento(id,imagenes);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //  @PreAuthorize("hasRole('ROLE_ADMIN')")

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> EliminarMicroEmprendimiento(@PathVariable Long id) {

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

    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<?> BuscarPorNombre(@PathVariable String nombre){


        try {

            return ResponseEntity.ok(microEmprendimientoService.buscarPorNombre(nombre));

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }


    }
    //  @PreAuthorize("hasRole('ROLE_ADMIN')")

    @DeleteMapping("/ocultar/{id}")
    public ResponseEntity<?> OcultarMicroEmprendimiento(@PathVariable Long id) {

        try {
            microEmprendimientoService.ocultarMicroEmprendimiento(id);

            return ResponseEntity.noContent().build();

        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }


    }
}
