package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.dtos.Cant_Mic_RubroDTO;
import com.semillero.ubuntu.dtos.MicroEmprendimientoDto;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.entities.Pais;
import com.semillero.ubuntu.entities.Provincia;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.repositories.MicroEmprendimientoRepository;
import com.semillero.ubuntu.repositories.PaisRepositorio;
import com.semillero.ubuntu.repositories.ProvinciaRepositorio;
import com.semillero.ubuntu.services.MicroEmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MicroEmprendimientoImpl implements MicroEmprendimientoService {

    @Autowired
    private MicroEmprendimientoRepository microEmprendimientoRepository;
    @Autowired
    private PaisRepositorio paisRepositorio;
    @Autowired
    private ProvinciaRepositorio provinciaRepositorio;

    @Override
    @Transactional
    public MicroEmprendimiento CrearMicroEmprendimiento(MicroEmprendimiento microEmprendimiento, Integer idPais, Integer idProvincia) {
        Optional<Pais> oP = paisRepositorio.findById(idPais);
        Optional<Provincia> oProv = provinciaRepositorio.findById(idProvincia);
        if (oP.isPresent() && oP.isPresent()) {
            microEmprendimiento.setPais(oP.get());
            microEmprendimiento.setProvincia(oProv.get());
            microEmprendimiento.setDeleted(false);
        }
        return microEmprendimientoRepository.save(microEmprendimiento);
    }

    @Override
    public Optional<MicroEmprendimiento> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void EditarMicroEmprendimiento(Long id, Integer idPais, Integer idProvincia, MicroEmprendimiento microEmprendimientoRequest) {

        Optional<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findById(id);
        Optional<Pais> oP = paisRepositorio.findById(idPais);
        Optional<Provincia> oProv = provinciaRepositorio.findById(idProvincia);
        if (respuesta.isPresent()) {
            System.out.println("micro encontrado");
            MicroEmprendimiento microEmprendimiento = respuesta.get();

            if (oP.isPresent() && oProv.isPresent()) {
                microEmprendimiento.setPais(oP.get());
                microEmprendimiento.setProvincia(oProv.get());
                System.out.println("pais y provincia encontrado");
            }
            microEmprendimiento.setNombre(microEmprendimientoRequest.getNombre());
            microEmprendimiento.setDescripcion(microEmprendimientoRequest.getDescripcion());
            microEmprendimiento.setMasInformacion(microEmprendimientoRequest.getMasInformacion());
            microEmprendimiento.setPais(microEmprendimiento.getPais());
            microEmprendimiento.setProvincia(microEmprendimiento.getProvincia());
            microEmprendimiento.setCiudad(microEmprendimiento.getCiudad());
            microEmprendimiento.setRubro(microEmprendimiento.getRubro());
            microEmprendimiento.setSubRubro(microEmprendimiento.getSubRubro());
            microEmprendimiento.setFechaCreacion(microEmprendimiento.getFechaCreacion());
            //microEmprendimiento.setMensajeContacto(microEmprendimiento.getMensajeContacto());
            //microEmprendimiento.setImagen(microEmprendimiento.getImagen());
            microEmprendimiento.setDeleted(microEmprendimiento.isDeleted());
            microEmprendimiento.setGestionado(microEmprendimiento.isGestionado());
            microEmprendimiento.setCiudad(microEmprendimientoRequest.getCiudad());
            microEmprendimiento.setRubro(microEmprendimientoRequest.getRubro());
            microEmprendimiento.setSubRubro(microEmprendimientoRequest.getSubRubro());
            System.out.println("antes de guardar");
            microEmprendimientoRepository.save(microEmprendimiento);
        }

    }

    @Override
    public void EliminarMicroEmprendimiento(Long id) throws ExceptionCreados {

        Optional<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findById(id);


        if (respuesta.isPresent()) {

            MicroEmprendimiento microEmprendimiento = respuesta.get();
            microEmprendimiento.setDeleted(true);
            microEmprendimientoRepository.save(microEmprendimiento);

        }

    }

    @Override
    public List<MicroEmprendimientoDto> ListarMicroEmprendimientos() throws ExceptionCreados {


        List<MicroEmprendimiento> respuesta = microEmprendimientoRepository.findByDeletedFalse();

        if (respuesta.isEmpty()) {

            throw new ExceptionCreados("No se encontraron microemprendimientos");

        } else {

            List<MicroEmprendimientoDto> dtos = respuesta.stream().map(m -> convertirADTO(m)).collect(Collectors.toList());
            return dtos;

        }

    }

    @Override
    public List<MicroEmprendimientoDto> buscarPorNombre(String nombre) throws ExceptionCreados {

        List<MicroEmprendimiento> respuesta = microEmprendimientoRepository.buscarPorNombre(nombre);

        if (respuesta.isEmpty()) {

            throw new ExceptionCreados("No se encontraron microemprendimientos");

        } else {

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

    public MicroEmprendimientoDto convertirADTO(MicroEmprendimiento microEmprendimiento) {

        MicroEmprendimientoDto dto = new MicroEmprendimientoDto();

        dto.setId(microEmprendimiento.getId());
        dto.setNombre(microEmprendimiento.getNombre());
        dto.setDescripcion(microEmprendimiento.getDescripcion());
        dto.setMasInformacion(microEmprendimiento.getMasInformacion());
        if (microEmprendimiento.getPais() != null) {
            dto.setPais(microEmprendimiento.getPais().getNombre());
        }
        if (microEmprendimiento.getProvincia() != null) {
            dto.setProvincia(microEmprendimiento.getProvincia().getNombre());
        }
        dto.setCiudad(microEmprendimiento.getCiudad());
        if (microEmprendimiento.getRubro() != null) {
            dto.setRubro(microEmprendimiento.getRubro().getNombre());
        }
        dto.setSubRubro(microEmprendimiento.getSubRubro());
        if (microEmprendimiento.getImagenes() != null) {
            dto.setImagenes(microEmprendimiento.getImagenes());
        }
        if (microEmprendimiento.getImagenes() != null) {
            dto.setImagenes(microEmprendimiento.getImagenes());
        }


        if (microEmprendimiento.getPais() != null) {
            dto.setPaisId(microEmprendimiento.getPais().getId());
        }
        if (microEmprendimiento.getProvincia() != null) {
            dto.setProvinciaId(microEmprendimiento.getProvincia().getId());
        }
        if (microEmprendimiento.getRubro() != null) {
            dto.setRubroId(microEmprendimiento.getRubro().getId());
        }
        return dto;
    }


    public List<Cant_Mic_RubroDTO> obtenerCantidadPorRubro() {

        try {
            List<Object[]> resultados = microEmprendimientoRepository.obtenerCantidadPorRubro();
            List<Cant_Mic_RubroDTO> cantidad = new ArrayList<>();

            for (Object[] resultado : resultados) {
                String categoria = (String) resultado[0];
                Long cantidad_micro = (Long) resultado[1];

                Cant_Mic_RubroDTO cantdto = new Cant_Mic_RubroDTO(categoria, cantidad_micro);
                cantidad.add(cantdto);
            }
            return cantidad;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la cantidad por Rubro", e);
        }

    }

}


