package com.semillero.ubuntu.entities;

import com.semillero.ubuntu.enums.NiveldeRiesgo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "inversiones")
public class Inversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long monto;
    private Long cuotas;
    private Long retorno;
    private Long ganancias;
    private Long monto_mensual;
    private Long cuotas_faltantes;
    private LocalDate fecha_creacion;
    private Boolean estado;

    @ManyToOne
    private Riesgo riesgo;

    @ManyToOne
    //@JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    //@JoinColumn(name = "id_microemprendimiento")
    private MicroEmprendimiento microEmprendimiento;

}
