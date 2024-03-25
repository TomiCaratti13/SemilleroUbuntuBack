package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Inversiones;
import com.semillero.ubuntu.entities.Publicacion;
import org.springframework.http.ResponseEntity;

public interface InversionService {

    ResponseEntity<?> saveInversion(Inversiones inversiones);
    ResponseEntity<?> editInversion(Inversiones inversiones,Long id);
    ResponseEntity<?> findAll();
    ResponseEntity<?> calc_inversion(Inversiones inversiones);


}
