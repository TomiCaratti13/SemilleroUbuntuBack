package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.enums.Rol;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import org.springframework.http.ResponseEntity;


public interface UsuarioInterfaz {
    ResponseEntity<?> crearUsuario(Usuario usuario);
    ResponseEntity<?> modificarUsuario(Long id, String nombre, String apellido, String email, String password, String telefono, Rol rol) throws ExceptionCreados;
    void desactivarUsuario(Long id) throws ExceptionCreados;
}
