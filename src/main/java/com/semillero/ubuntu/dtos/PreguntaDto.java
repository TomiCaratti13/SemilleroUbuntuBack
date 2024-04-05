package com.semillero.ubuntu.dtos;

import lombok.Data;

@Data
public class PreguntaDto {
    private Long id;
    private String pregunta;
    private RespuestaDto respuesta;

    private Boolean deleted;
    private Boolean inicial;
}
