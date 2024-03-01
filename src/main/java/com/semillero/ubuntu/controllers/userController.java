package com.semillero.ubuntu.controllers;
import com.semillero.ubuntu.dtos.UsuarioDTO;
import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {
    private final UsuarioServiceImpl usuarioService;

    @Autowired
    public userController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }
/*
    @PostMapping(value ="/save")
    public ResponseEntity<?> userLogin(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario)) ;
    }*/
    @PostMapping(value ="/save")
    public ResponseEntity<UsuarioDTO> userLogin(@RequestBody Usuario usuario){
        Usuario usuarioGuardado = usuarioService.crearUsuario(usuario);
        UsuarioDTO usuarioDTO = convertirUsuarioAUsuarioDTO(usuarioGuardado);
        return ResponseEntity.ok(usuarioDTO);
    }

    private UsuarioDTO convertirUsuarioAUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setRol(usuario.getRol());
        // Establece otros campos necesarios
        return usuarioDTO;
    }
}

