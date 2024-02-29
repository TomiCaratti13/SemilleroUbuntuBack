package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.enums.Rol;
import com.semillero.ubuntu.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepository;
    @Transactional
    public Usuario crearUsuario(String nombre, String apellido, String email, String password, String telefono) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setRol(Rol.ADMINISTRADORES);
        usuario.setDeleted(false); // Asumiendo que false significa activo
        return usuarioRepository.save(usuario);
    }
    @Transactional
    public Usuario modificarUsuario(Long id,String nombre, String apellido, String email, String password, String telefono,Rol rol) {
        Usuario usuario = usuarioRepository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setRol(rol);
        usuario.setDeleted(false); // Asumiendo que false significa activo
        return usuarioRepository.save(usuario);
    }
    @Transactional
    public void desactivarUsuario(int id) {
        Usuario usuario = usuarioRepository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setDeleted(true);
        usuarioRepository.save(usuario);
    }
}
