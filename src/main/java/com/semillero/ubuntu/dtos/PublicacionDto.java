package com.semillero.ubuntu.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.semillero.ubuntu.entities.Imagen;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.awt.*;
import java.util.Date;
import java.util.List;

@Data
public class PublicacionDto {
    private Long id;
    private String titulo;
    private String descripcion;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaCreacion;
    private Integer visualizaciones;
    @OneToMany
    private List<Imagen> imagenes;
}
