package com.semillero.ubuntu.services;


import com.semillero.ubuntu.dtos.MicroEmprendimientoRequest;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.repositories.MicroEmprendimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MicroEmprendimientoService {


    @Autowired
    MicroEmprendimientoRepository microEmprendimientoRepository;

    @Transactional
    public void CrearMicroEmprendimiento(MicroEmprendimiento microEmprendimiento) {
        microEmprendimientoRepository.save(microEmprendimiento);
    }
    public Optional<MicroEmprendimiento> findById(Long id) {
        return  microEmprendimientoRepository.findById(id);
    }

    @Transactional
    public void EditarMicroEmprendimiento(Long id, MicroEmprendimientoRequest microEmprendimientoRequest){

        Optional<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findById(id);

        if(respuesta.isPresent()){

            MicroEmprendimiento microEmprendimiento = respuesta.get();

            microEmprendimiento.setNombre(microEmprendimientoRequest.getNombre());
            microEmprendimiento.setDescripcion(microEmprendimiento.getDescripcion());
            microEmprendimiento.setMasInformacion(microEmprendimiento.getMasInformacion());
            microEmprendimiento.setPais(microEmprendimiento.getPais());
            microEmprendimiento.setProvincia(microEmprendimiento.getProvincia());
            microEmprendimiento.setCiudad(microEmprendimiento.getCiudad());
            microEmprendimiento.setRubro(microEmprendimiento.getRubro());
            microEmprendimiento.setSubRubro(microEmprendimiento.getSubRubro());
            //microEmprendimiento.setMensajeContacto(microEmprendimiento.getMensajeContacto());
            //microEmprendimiento.setImagen(microEmprendimiento.getImagen());
            microEmprendimiento.setDeleted(microEmprendimiento.isDeleted());
            microEmprendimiento.setGestionado(microEmprendimiento.isGestionado());

            microEmprendimientoRepository.save(microEmprendimiento);

        }
    }

    public void EliminarMicroEmprendimiento(Long id)throws ExceptionCreados{

            Optional<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findById(id);

            if(respuesta.isPresent()){

                MicroEmprendimiento microEmprendimiento = respuesta.get();
                microEmprendimiento.setDeleted(true);
                microEmprendimientoRepository.save(microEmprendimiento);

            }

    }

    public List<MicroEmprendimiento> ListarMicroEmprendimientos()throws ExceptionCreados{

        List<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findAll();
        if(respuesta.isEmpty()){

            throw new ExceptionCreados("No se encontraron microemprendimientos");

        }else {

            return respuesta;

        }

    }

    public List<MicroEmprendimiento> buscarPorNombre(String nombre)throws ExceptionCreados{

        List<MicroEmprendimiento> respuesta = microEmprendimientoRepository.buscarPorNombre(nombre);

        if(respuesta.isEmpty()) {

            throw new ExceptionCreados("No se encontraron microemprendimientos");

        }else{

            return respuesta;

        }

    }

    public void ocultarMicroEmprendimiento(Long id) {
        Optional<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findById(id);
        if (respuesta.isPresent()) {
            MicroEmprendimiento microEmprendimiento = respuesta.get();
            microEmprendimiento.setDeleted(true);
            microEmprendimientoRepository.save(microEmprendimiento);
        }
    }


}
