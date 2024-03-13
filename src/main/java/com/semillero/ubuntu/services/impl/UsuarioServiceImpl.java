package com.semillero.ubuntu.services.impl;
import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.enums.Rol;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.repositories.UsuarioRepositorio;
import com.semillero.ubuntu.services.UsuarioInterfaz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioInterfaz {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setRol(Rol.ADMINISTRADORES);
        usuario.setDeleted(false);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario modificarUsuario(Long id, String nombre, String apellido, String email, String password, String telefono, Rol rol) throws ExceptionCreados {
        Usuario usuario = new Usuario();
        try {
            Optional<Usuario> respuesta = usuarioRepository.findById(String.valueOf(id));
            if (respuesta.isPresent()) {
                usuario = respuesta.get();
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setEmail(email);
                usuario.setPassword(password);
                usuario.setTelefono(telefono);
                usuario.setRol(rol);
                usuario.setDeleted(false);
                return usuarioRepository.save(usuario);
            }
        } catch (Exception e) {
            throw new ExceptionCreados("Usuario no encontrado" + e.getMessage());
        }
        return usuario;
    }

    @Override
    @Transactional
    public void desactivarUsuario(Long id) throws ExceptionCreados {
        try {
            Optional<Usuario> respuesta = usuarioRepository.findById(String.valueOf(id));
            if (respuesta.isPresent()) {
                Usuario usuario = respuesta.get();
                usuario.setDeleted(true);
                usuarioRepository.save(usuario);
            }
        } catch (Exception e) {
            throw new ExceptionCreados("Usuario no encontrado" + e.getMessage());
        }
    }

}
