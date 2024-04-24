package com.semillero.ubuntu.controllers;
import com.semillero.ubuntu.entities.Inversion;
import com.semillero.ubuntu.entities.Provincia;
import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class userController {
    private final UsuarioServiceImpl usuarioService;


    @Autowired
    public userController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value ="/save")
    public ResponseEntity<?> userLogin(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }



}

