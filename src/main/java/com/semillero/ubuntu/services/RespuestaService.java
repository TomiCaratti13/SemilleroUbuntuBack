package com.semillero.ubuntu.services;

import com.semillero.ubuntu.dtos.RespuestaDto;
import com.semillero.ubuntu.entities.Respuesta;

import java.util.List;

public interface RespuestaService {
    Respuesta save(Respuesta respuesta);
    RespuestaDto findById(Long id);
    List<RespuestaDto> findAll();
}
