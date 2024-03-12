package com.semillero.ubuntu.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "titulo es un campo obligatorio")
    private String titulo;
    @NotNull(message = "titulo es un campo obligatorio")
    private String descripcion;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    private Integer visualizaciones;
    private boolean deleted;
//    @OneToMany
//    private List<Imagen> imagenes;

}
