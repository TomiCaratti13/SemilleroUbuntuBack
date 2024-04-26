package com.semillero.ubuntu.entities;

import com.semillero.ubuntu.enums.NiveldeRiesgo;
import com.semillero.ubuntu.enums.Rol;
import com.semillero.ubuntu.services.InversionService;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "riesgo")
public class Riesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private Long costo;
    private Long cuotas;
    private String descripcion;
    private Long invMinima;
    private Long invMaxima;
    @Enumerated(EnumType.STRING)
    private NiveldeRiesgo nombre_riesgo;
    private Double tasa_retorno;
    private Double factor_riesgo;

}
