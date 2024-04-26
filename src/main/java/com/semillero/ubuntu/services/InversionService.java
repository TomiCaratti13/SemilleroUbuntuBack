package com.semillero.ubuntu.services;

import com.semillero.ubuntu.dtos.CalculoDto;
import com.semillero.ubuntu.dtos.InversionDTO;
import com.semillero.ubuntu.entities.Inversion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InversionService {

    @Transactional
    public Inversion crearInversion(CalculoDto calculoDto, Long idRiesgo, Long idUser1, Long idMicro);

    public List<InversionDTO> obtenerMisInversiones(Long id);


}
