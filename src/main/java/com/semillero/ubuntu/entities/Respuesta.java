package com.semillero.ubuntu.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String respuestaText;
    @OneToMany
    private List<Pregunta> preguntas;
}
