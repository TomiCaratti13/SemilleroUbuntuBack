package com.semillero.ubuntu.dtos;

import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoRiesgo {

    private Long id;
    private String nombre;
    private Long invMinima;
    private Long invMaxima;

}
