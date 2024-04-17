package com.semillero.ubuntu.controllers;


import com.semillero.ubuntu.dtos.Cant_Mic_RubroDTO;
import com.semillero.ubuntu.dtos.MicroEmprendimientoDto;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.services.MicroEmprendimientoService;
import com.semillero.ubuntu.services.impl.cargaImagenImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/microEmprendimiento")
@CrossOrigin(origins = "*")
public class MicroEmprendimientoController {

    @Autowired
    private cargaImagenImpl serviceImagen;

    @Autowired
    private MicroEmprendimientoService microEmprendimientoService;

    //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear/{idPais}/{idProvincia}")
    public ResponseEntity<?> CrearMicroEmpendimiento(@RequestBody MicroEmprendimiento microEmprendimiento, @PathVariable Integer idPais, @PathVariable Integer idProvincia) {


        try {

            microEmprendimiento.setFechaCreacionNow();


            MicroEmprendimiento micro = microEmprendimientoService.CrearMicroEmprendimiento(microEmprendimiento, idPais, idProvincia);

            return ResponseEntity.status(HttpStatus.CREATED).body(micro.getId());

        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping("/crearImagenes/{id}")
    public ResponseEntity<?> CrearMicroEmpendimientoImagenes(@PathVariable("id") Long id, @RequestParam("imagenes") List<MultipartFile> imagenes) {

        try {

            return serviceImagen.cargarImagenMicroemprendimiento(id, imagenes);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //  @PreAuthorize("hasRole('ROLE_ADMIN')")

    @PutMapping("/editar/{id}/{idPais}/{idProvincia}")
    public ResponseEntity<?> EditarMicroEmpendimiento(@PathVariable Long id,@PathVariable Integer idPais,@PathVariable Integer idProvincia, @RequestBody MicroEmprendimiento microEmprendimientoRequest) {
        microEmprendimientoService.EditarMicroEmprendimiento(id, idPais, idProvincia, microEmprendimientoRequest);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/editarImagenes/{id}")
    public ResponseEntity<?> EditarMicroEmpendimientoImagenes(@PathVariable Long id, @RequestParam("imagenes") List<MultipartFile> imagenes) {

        try {
            return serviceImagen.modificarImagenMicroemprendimiento(id, imagenes);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //  @PreAuthorize("hasRole('ROLE_ADMIN')")

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> EliminarMicroEmprendimiento(@PathVariable Long id) {

        try {
            microEmprendimientoService.EliminarMicroEmprendimiento(id);

            return ResponseEntity.noContent().build();

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/listar")
    public ResponseEntity<?> ListarMicroEmprendimientos() {

        try {
            System.out.println("entre al controlador");
            return ResponseEntity.ok(microEmprendimientoService.ListarMicroEmprendimientos());

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<?> BuscarPorNombre(@PathVariable String nombre) {


        try {

            return ResponseEntity.ok(microEmprendimientoService.buscarPorNombre(nombre));

        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }


    }
    //  @PreAuthorize("hasRole('ROLE_ADMIN')")

    @DeleteMapping("/ocultar/{id}")
    public ResponseEntity<?> OcultarMicroEmprendimiento(@PathVariable Long id) {

        try {
            microEmprendimientoService.ocultarMicroEmprendimiento(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/cantidades_por_rubro")

    public ResponseEntity<List<Cant_Mic_RubroDTO>> obtenerCantidadesPorRubro(){

        try {
            List<Cant_Mic_RubroDTO> resultado = microEmprendimientoService.obtenerCantidadPorRubro();
            return new ResponseEntity<>(resultado,HttpStatus.OK) ;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }





}





