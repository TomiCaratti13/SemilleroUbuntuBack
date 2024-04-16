package com.semillero.ubuntu.services;

import com.semillero.ubuntu.dtos.CalculoDto;
import com.semillero.ubuntu.dtos.Cant_Mic_RubroDTO;
import com.semillero.ubuntu.dtos.DtoRiesgo;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.entities.Riesgo;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RiesgoService {

    @Transactional
    public Riesgo crearRiesgo(Riesgo riesgo) ;

    public List<DtoRiesgo> listaNivel() throws Exception;

    ResponseEntity<CalculoDto> calculo(Long id, Long monto);

}
