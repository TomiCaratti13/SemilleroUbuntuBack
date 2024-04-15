package com.semillero.ubuntu.services.impl;


import com.semillero.ubuntu.dtos.Cant_Mic_RubroDTO;
import com.semillero.ubuntu.dtos.DtoRiesgo;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.entities.Riesgo;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.repositories.RiesgoRepository;
import com.semillero.ubuntu.services.RiesgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

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
    public List<DtoRiesgo> listaNivel(){
        try {
            List<Riesgo> respuesta = riesgoRepository.findAll();
            if (respuesta.isEmpty()) {

                throw new ExceptionCreados("No se encontraron Riesgos");

            } else {

                return respuesta.stream().map(riesgo -> convertir(riesgo)).collect(Collectors.toList());

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener Nivel de Riesgo",e);
        }

    }

    public DtoRiesgo convertir(Riesgo riesgo){
        DtoRiesgo dtoRiesgo = new DtoRiesgo();
        dtoRiesgo.setId(riesgo.getId());
        dtoRiesgo.setNombre(String.valueOf(riesgo.getNombre_riesgo()));
        return dtoRiesgo;
    }





}
