package com.semillero.ubuntu.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MicroEmprendimientoDto implements Serializable {

    private Long id;
    private String nombre;
    private String descripcion;
    private String masInformacion;
    private String pais;
    private String provincia;
    private String ciudad;
    private String rubro;
    private String subRubro;
    private List<String> mensajeContacto;



}
