package com.semillero.ubuntu.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.semillero.ubuntu.entities.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class ContactoDto {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaCreacion;
    private String descripcion;
    private Usuario usuarioSolicitante;
    private String microemprendimiento;
    private boolean gestionado;

}
