package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.dtos.RespuestaDto;
import com.semillero.ubuntu.dtos.RespuestaPreguntaDto;
import com.semillero.ubuntu.entities.Respuesta;
import com.semillero.ubuntu.repositories.RespuestaRepository;
import com.semillero.ubuntu.services.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository repository;

    @Override
    public Respuesta save(Respuesta respuesta) {
        return repository.save(respuesta);
    }

    @Override
    public RespuestaDto findById(Long id) {
        Respuesta respuesta = repository.findById(id).orElse(null);
        return resp(respuesta);
    }

    @Override
    public List<RespuestaDto> findAll() {
        List<Respuesta> allRespuestas = repository.findAll();
        List<RespuestaDto> dtoRespuestas = new ArrayList<>();

        allRespuestas.stream().map(p -> dtoRespuestas.add(resp(p)));
        return dtoRespuestas;
    }

    public RespuestaDto resp(Respuesta respuesta){
        RespuestaDto dto = new RespuestaDto();
        if(respuesta!= null){
            dto.setRespuesta(respuesta.getRespuestaText());

            List<RespuestaPreguntaDto> listPreguntas= new ArrayList<>();
            respuesta.getPreguntas().stream().forEach(p -> {
                RespuestaPreguntaDto respuestaPreguntaDto = new RespuestaPreguntaDto(p.getId(),p.getPregunta());
                listPreguntas.add(respuestaPreguntaDto);
            });
            dto.setPreguntas(listPreguntas);
        }
        return dto;
    }

}
