package com.semillero.ubuntu.services;

import com.semillero.ubuntu.dtos.PreguntaDto;
import com.semillero.ubuntu.entities.Pregunta;

public interface PreguntaService {
    Pregunta save(Pregunta pregunta);
    PreguntaDto findById(Long id);
}
