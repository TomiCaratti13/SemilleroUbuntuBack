package com.semillero.ubuntu.services;

import com.semillero.ubuntu.dtos.CalculoDto;
import com.semillero.ubuntu.entities.Inversion;

public interface InversionService {
    Inversion crearInversion(CalculoDto calculoDto, Long idRiesgo, Long idUser1, Long idMicro);


}
