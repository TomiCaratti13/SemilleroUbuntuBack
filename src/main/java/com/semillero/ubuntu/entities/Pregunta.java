package com.semillero.ubuntu.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(length = 1000)
    private String pregunta;
    @OneToOne
    private Respuesta respuesta;

    private Boolean deleted;
    private Boolean inicial;





}
