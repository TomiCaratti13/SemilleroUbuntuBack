package com.semillero.ubuntu.services;


import com.semillero.ubuntu.dtos.MicroEmprendimientoDto;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


public interface MicroEmprendimientoService {


    @Transactional
    public void CrearMicroEmprendimiento(MicroEmprendimiento microEmprendimiento,Integer idPais,Integer idProvincia) ;

    public Optional<MicroEmprendimiento> findById(Long id);


    @Transactional
    public void EditarMicroEmprendimiento(Long id, MicroEmprendimientoDto microEmprendimientoRequest);

    public void EliminarMicroEmprendimiento(Long id)throws ExceptionCreados;

    public List<MicroEmprendimientoDto> ListarMicroEmprendimientos()throws ExceptionCreados;

    public List<MicroEmprendimientoDto> buscarPorNombre(String nombre)throws ExceptionCreados;

    public void ocultarMicroEmprendimiento(Long id);


}
