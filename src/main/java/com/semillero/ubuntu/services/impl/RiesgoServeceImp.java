package com.semillero.ubuntu.services.impl;


import com.semillero.ubuntu.dtos.CalculoDto;
import com.semillero.ubuntu.dtos.DtoRiesgo;
import com.semillero.ubuntu.entities.Riesgo;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.repositories.RiesgoRepository;
import com.semillero.ubuntu.services.RiesgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RiesgoServeceImp implements RiesgoService {

    @Autowired
    private RiesgoRepository riesgoRepository;

    @Override
    @Transactional
    public Riesgo crearRiesgo(Riesgo riesgo) {
        return riesgoRepository.save(riesgo);
    }

    @Override
    public List<DtoRiesgo> listaNivel() {
        try {
            List<Riesgo> respuesta = riesgoRepository.findAll();
            if (respuesta.isEmpty()) {

                throw new ExceptionCreados("No se encontraron Riesgos");

            } else {

                return respuesta.stream().map(riesgo -> convertir(riesgo)).collect(Collectors.toList());

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener Nivel de Riesgo", e);
        }

    }


    public DtoRiesgo convertir(Riesgo riesgo) {
        DtoRiesgo dtoRiesgo = new DtoRiesgo();
        dtoRiesgo.setId(riesgo.getId());
        dtoRiesgo.setNombre(String.valueOf(riesgo.getNombre_riesgo()));
        dtoRiesgo.setInvMinima(riesgo.getInvMinima());
        dtoRiesgo.setInvMaxima(riesgo.getInvMaxima());
        return dtoRiesgo;
    }


    @Override
    public ResponseEntity<CalculoDto> calculo(Long id, Long monto) {
        Optional<Riesgo> riesgo = riesgoRepository.findById(id);
        CalculoDto calculoDto = new CalculoDto();
        if (riesgo.isPresent() && monto <= riesgo.get().getInvMaxima() && monto >= riesgo.get().getInvMinima()) {
            calculoDto.setCosto(riesgo.get().getCosto());
            calculoDto.setTotal(riesgo.get().getCosto() + monto);
            calculoDto.setCuotas(riesgo.get().getCuotas());
            calculoDto.setTasa(riesgo.get().getTasa_retorno());
            calculoDto.setRetorno((long) (monto * riesgo.get().getTasa_retorno() *
                    riesgo.get().getFactor_riesgo()));
            calculoDto.setGanancias(calculoDto.getRetorno() - calculoDto.getTotal());
            return ResponseEntity.ok(calculoDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
