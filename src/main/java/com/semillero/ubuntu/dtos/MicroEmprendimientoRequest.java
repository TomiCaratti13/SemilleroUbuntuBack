package com.semillero.ubuntu.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MicroEmprendimientoRequest implements Serializable {

    private Long id;
    private String nombre;
    private String descripcion;
    private String masInformacion;
    private String pais;
    private String provincia;
    private String ciudad;
    private String rubro;
    private String subRubro;
    private String mensajeContacto;
    private boolean deleted;
    private boolean gestionado;


}
