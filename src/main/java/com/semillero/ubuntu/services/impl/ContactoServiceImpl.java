package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.dtos.ContactoDto;
import com.semillero.ubuntu.dtos.mapper.DtoMapperContacto;
import com.semillero.ubuntu.entities.Contacto;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.enums.Rol;
import com.semillero.ubuntu.repositories.ContactoRepository;
import com.semillero.ubuntu.repositories.MicroEmprendimientoRepository;

import com.semillero.ubuntu.repositories.UsuarioRepositorio;
import com.semillero.ubuntu.services.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactoServiceImpl implements ContactoService {
    @Autowired
    private ContactoRepository repository;

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private MicroEmprendimientoRepository microEmprendimientoRepository;

    @Override
    public ResponseEntity<?> save(Contacto contacto, Long idMicroemprendimiento) {
        Optional<MicroEmprendimiento> o = microEmprendimientoRepository.findById(idMicroemprendimiento);
        if (o.isPresent()) {

            // MicroEmprendimiento emprendimiento = o.get();
            // contacto.setFechaCreacion(new Date());
            // contacto.setMicroEmprendimiento(emprendimiento);
            // Usuario usuario = contacto.getUsuarioSolicitante();
            // usuario.setRole(Rol.INVER);
            // usuarioRepository.save(usuario);
            // Contacto contactoDb = repository.save(contacto);
            // emprendimiento.addContactos(contactoDb);
            // microEmprendimientoRepository.save(emprendimiento);
            // return ResponseEntity.ok().build();

            MicroEmprendimiento emprendimiento = o.get();
            contacto.setFechaCreacion(new Date());
            contacto.setMicroEmprendimiento(emprendimiento);
            usuarioRepository.save(contacto.getUsuarioSolicitante());
            Contacto contactoDb = repository.save(contacto);
            emprendimiento.addContactos(contactoDb);
            microEmprendimientoRepository.save(emprendimiento);
            return ResponseEntity.ok().build();
        }


        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<ContactoDto> contactoDtoList = repository.findAll().stream().map(c -> DtoMapperContacto.getInstance().setContacto(c).build()).collect(Collectors.toList());
        return ResponseEntity.ok(contactoDtoList);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<Contacto> o = repository.findById(id);
        if (o.isPresent()) {
            Contacto contacto = o.get();
            return ResponseEntity.ok(DtoMapperContacto.getInstance().setContacto(contacto).build());
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> gestionar(Long id) {
        Optional<Contacto> o = repository.findById(id);
        if (o.isPresent()) {
            Contacto contacto = o.get();
            contacto.setGestionado(true);
            repository.save(contacto);
            return ResponseEntity.ok(DtoMapperContacto.getInstance().setContacto(contacto).build());
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> deleted(Long id) {
        return null;
    }
}
