package com.semillero.ubuntu.dtos;


import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisualizacionesDTO {

    private String titulo;
    private String descripcion;
    private Date fecha_creacion;
    private Integer visualizaciones;

}
