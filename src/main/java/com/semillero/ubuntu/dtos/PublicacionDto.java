package com.semillero.ubuntu.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
@Data
public class PublicacionDto {
    private Long id;
    private String titulo;
    private String descripcion;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaCreacion;
    private Integer visualizaciones;
//    @OneToMany
//    private List<Imagen> imagenes;
}
