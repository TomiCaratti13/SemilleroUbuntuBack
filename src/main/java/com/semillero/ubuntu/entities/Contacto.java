package com.semillero.ubuntu.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaCreacion;
    private String descripcion;
    @OneToOne
    private Usuario usuarioSolicitante;
    @ManyToOne
    private MicroEmprendimiento microEmprendimiento;
    private boolean gestionado;
}
