package com.semillero.ubuntu.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class RespuestaDto {
    private String respuesta;
    private List<RespuestaPreguntaDto> preguntas = new ArrayList<>();
}
