package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.dtos.PreguntaDto;
import com.semillero.ubuntu.entities.Pregunta;
import com.semillero.ubuntu.repositories.PreguntaRepository;
import com.semillero.ubuntu.services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository repository;
    @Autowired
    private RespuestaServiceImpl respuestaService;

    @Override
    public Pregunta save(Pregunta pregunta) {
        return repository.save(pregunta);
    }

    @Override
    public PreguntaDto findById(Long id) {
        Optional<Pregunta> o = repository.findById(id);
        if (o.isPresent()) {
            PreguntaDto dto = new PreguntaDto();

            Pregunta pregunta = o.get();
            dto.setId(pregunta.getId());
            dto.setPregunta(pregunta.getPregunta());
            dto.setRespuesta(respuestaService.findById(pregunta.getRespuesta().getId()));
            System.out.println(pregunta);
            return dto;
        }
        return null;
    }
}

