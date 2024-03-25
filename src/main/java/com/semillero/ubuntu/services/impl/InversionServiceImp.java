package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.entities.Inversiones;
import com.semillero.ubuntu.repositories.InversionRepository;
import com.semillero.ubuntu.services.InversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InversionServiceImp implements InversionService {

    @Autowired
    private InversionRepository inversionRepository;


    @Transactional
    @Override
    public ResponseEntity<?> saveInversion(Inversiones inversiones) {
        inversiones.getUsuario_id().getId();

        return ResponseEntity.status(201).body(inversionRepository.save(inversiones));
    }

    @Transactional
    @Override
    public ResponseEntity findAll() {
        return ResponseEntity.ok(inversionRepository.findAll());
    }

    @Transactional
    @Override
    public ResponseEntity<?> editInversion(Inversiones inversiones, Long id) {
        Optional<Inversiones> opInver = inversionRepository.findById(String.valueOf(id));
        if (opInver.isPresent()) {
            Inversiones inveredit = opInver.get();
            inveredit.setCuotas(inversiones.getCuotas());
            inveredit.setTasa_de_retorno(inversiones.getTasa_de_retorno());
            return ResponseEntity.status(201).body(inversionRepository.save(inveredit));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @Override
    public ResponseEntity<?> calc_inversion(Inversiones inversiones){

    return null;



    }

}
