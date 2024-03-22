package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.dtos.MicroEmprendimientoDto;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.repositories.MicroEmprendimientoRepository;
import com.semillero.ubuntu.services.MicroEmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MicroEmprendimientoImpl implements MicroEmprendimientoService {

    @Autowired
    private MicroEmprendimientoRepository microEmprendimientoRepository;

    @Override
    @Transactional
    public void CrearMicroEmprendimiento(MicroEmprendimiento microEmprendimiento) {
        microEmprendimientoRepository.save(microEmprendimiento);
    }
    @Override
    public Optional<MicroEmprendimiento> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void EditarMicroEmprendimiento(Long id, MicroEmprendimientoDto microEmprendimientoRequest){

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
            //      microEmprendimiento.setImagen(microEmprendimiento.getImagen());
            microEmprendimiento.setDeleted(microEmprendimiento.isDeleted());
            microEmprendimiento.setGestionado(microEmprendimiento.isGestionado());

            microEmprendimientoRepository.save(microEmprendimiento);

        }

    }

    @Override
    public void EliminarMicroEmprendimiento(Long id)throws ExceptionCreados{

        Optional<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findById(id);

        if(respuesta.isPresent()){

            MicroEmprendimiento microEmprendimiento = respuesta.get();
            microEmprendimiento.setDeleted(true);
            microEmprendimientoRepository.save(microEmprendimiento);

        }

    }
    @Override
    public List<MicroEmprendimientoDto> ListarMicroEmprendimientos()throws ExceptionCreados{

        List<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findAll();
        if(respuesta.isEmpty()){

            throw new ExceptionCreados("No se encontraron microemprendimientos");

        }else {

            return respuesta.stream().map(microEmprendimiento -> convertirADTO(microEmprendimiento)).collect(Collectors.toList());

        }

    }
    @Override
    public List<MicroEmprendimientoDto> buscarPorNombre(String nombre)throws ExceptionCreados{

        List<MicroEmprendimiento> respuesta = microEmprendimientoRepository.buscarPorNombre(nombre);

        if(respuesta.isEmpty()) {

            throw new ExceptionCreados("No se encontraron microemprendimientos");

        }else{

            return respuesta.stream().map(microEmprendimiento -> convertirADTO(microEmprendimiento)).collect(Collectors.toList());

        }

    }

    @Override
    public void ocultarMicroEmprendimiento(Long id) {
        Optional<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findById(id);
        if (respuesta.isPresent()) {
            MicroEmprendimiento microEmprendimiento = respuesta.get();
            microEmprendimiento.setDeleted(true);
            microEmprendimientoRepository.save(microEmprendimiento);
        }
    }

    public MicroEmprendimientoDto convertirADTO(MicroEmprendimiento microEmprendimiento){

        MicroEmprendimientoDto dto = new MicroEmprendimientoDto();

        dto.setId(microEmprendimiento.getId());
        dto.setNombre(microEmprendimiento.getNombre());
        dto.setDescripcion(microEmprendimiento.getDescripcion());
        dto.setMasInformacion(microEmprendimiento.getMasInformacion());
        //dto.setPais
        //dto.setProvincia();
        dto.setCiudad(microEmprendimiento.getCiudad());
        //dto.setRubro(microEmprendimiento.getRubro());
        dto.setSubRubro(microEmprendimiento.getSubRubro());
        //dto.setMensajeContacto(microEmprendimiento.getContactos());

        return dto;

            }
}
