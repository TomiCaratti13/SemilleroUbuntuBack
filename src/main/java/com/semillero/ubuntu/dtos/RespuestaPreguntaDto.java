package com.semillero.ubuntu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespuestaPreguntaDto {
    private Long id;
    private String pregunta;
}
