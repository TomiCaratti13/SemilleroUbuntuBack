package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.enums.Rol;


public interface UsuarioInterfaz {
    Usuario crearUsuario(Usuario usuario);
    Usuario modificarUsuario(Long id, String nombre, String apellido, String email, String password, String telefono, Rol rol);
    void desactivarUsuario(Long id);
}
