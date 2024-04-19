package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.dtos.CalculoDto;
import com.semillero.ubuntu.entities.Inversion;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.entities.Riesgo;
import com.semillero.ubuntu.entities.Usuario;
import com.semillero.ubuntu.repositories.InversionRepository;
import com.semillero.ubuntu.repositories.MicroEmprendimientoRepository;
import com.semillero.ubuntu.repositories.RiesgoRepository;
import com.semillero.ubuntu.repositories.UsuarioRepositorio;
import com.semillero.ubuntu.services.InversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class InversionServiceImp implements InversionService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private MicroEmprendimientoRepository microEmprendimientoRepository;

    @Autowired
    private RiesgoRepository riesgoRepository;
    @Autowired
    private InversionRepository inversionRepository;

    @Override
    @Transactional
    public Inversion crearInversion(CalculoDto calculoDto, Long idRiesgo, Long idUser, Long idMicro) {
        Optional<Usuario> oU = usuarioRepositorio.findById(idUser);
        Optional<MicroEmprendimiento> oM = microEmprendimientoRepository.findById(idMicro);
        Optional<Riesgo> oR = riesgoRepository.findById(idRiesgo);
        Inversion inversion = new Inversion();
        if (oU.isPresent() && oM.isPresent()) {
            inversion.setMonto(calculoDto.getTotal() - calculoDto.getCosto());
            inversion.setCuotas(calculoDto.getCuotas());
            inversion.setRetorno(calculoDto.getRetorno());
            inversion.setGanancias(calculoDto.getGanancias());
            inversion.setMonto_mensual(calculoDto.getRetorno() / calculoDto.getCuotas());
            inversion.setCuotas_faltantes(calculoDto.getCuotas());
            inversion.setFecha_creacion(LocalDate.from(LocalDateTime.now()));
            inversion.setRiesgo(oR.get());
            inversion.setUsuario(oU.get());
            inversion.setMicroEmprendimiento(oM.get());
            inversion.setEstado(true);
        }

        return inversionRepository.save(inversion);
    }




    @Override
    public List<Inversion> obtenerMisInversiones(Long id){
        Optional<Usuario> opUser = usuarioRepositorio.findById(id);
        if(opUser.isPresent()){
            return inversionRepository.obtenerMisInversiones(id);
        }else {
        return new ArrayList<>();}

    }



    }

