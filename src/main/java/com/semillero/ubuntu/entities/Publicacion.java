package com.semillero.ubuntu.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotBlank
    @Column(length = 2000)
    private String descripcion;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaCreacion;
    private Integer visualizaciones;
    private boolean deleted;
    @OneToMany()
    @Size(min = 1, max = 3)
    private List<Imagen> imagenes;

}
