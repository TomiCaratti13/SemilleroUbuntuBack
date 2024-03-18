package com.semillero.ubuntu.services.impl;
import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.enums.Rol;
import com.semillero.ubuntu.repositories.UsuarioRepositorio;
import com.semillero.ubuntu.services.UsuarioInterfaz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioInterfaz {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
       // usuario.setRol(Rol.ADMINISTRADORES);
        usuario.setDeleted(false);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario modificarUsuario(Long id, String nombre, String apellido, String email, String password, String telefono, Rol rol) {
        Usuario usuario = usuarioRepository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setRole(rol);
        usuario.setDeleted(false);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setDeleted(true);
        usuarioRepository.save(usuario);
    }
}
