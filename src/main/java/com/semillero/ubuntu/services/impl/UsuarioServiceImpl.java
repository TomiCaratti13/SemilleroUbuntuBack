package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.dtos.UsuarioDTO;
import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.enums.Rol;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.repositories.UsuarioRepositorio;

import com.semillero.ubuntu.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepositorio usuarioRepository;

    @Override
    @Transactional
    public ResponseEntity<?> crearUsuario(Usuario usuario) {
        usuario.setRole(Rol.ADMIN);
        usuario.setDeleted(false);
        usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = convertirUsuarioAUsuarioDTO(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<?> modificarUsuario(Long id, String nombre, String apellido, String email, String password, String telefono, Rol rol) throws ExceptionCreados {
        try {

            Optional<Usuario> respuesta = usuarioRepository.findById(id);
            if (respuesta.isPresent()) {
                Usuario usuario = respuesta.get();
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setEmail(email);
                usuario.setPassword(password);
                usuario.setTelefono(telefono);
                usuario.setRole(rol);
                usuario.setDeleted(false);
                usuarioRepository.save(usuario);
                UsuarioDTO usuarioDTO = convertirUsuarioAUsuarioDTO(usuario);
                return ResponseEntity.ok(usuarioDTO);
            }
        } catch (Exception e) {
            throw new ExceptionCreados("Usuario no encontrado" + e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @Transactional
    public void desactivarUsuario(Long id) throws ExceptionCreados {
        try {
            Optional<Usuario> respuesta = usuarioRepository.findById(id);
            if (respuesta.isPresent()) {
                Usuario usuario = respuesta.get();
                usuario.setDeleted(true);
                usuarioRepository.save(usuario);
            }
        } catch (Exception e) {
            throw new ExceptionCreados("Usuario no encontrado" + e.getMessage());
        }
    }

    private UsuarioDTO convertirUsuarioAUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setRole(usuario.getRole());
        return usuarioDTO;
    }

}
