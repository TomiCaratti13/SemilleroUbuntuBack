package com.semillero.ubuntu.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaCreacion;
    private String descripcion;
    @OneToOne
    private Usuario usuarioSolicitante;
    @ManyToOne
    private MicroEmprendimiento microEmprendimiento;
    private boolean gestionado;
}
