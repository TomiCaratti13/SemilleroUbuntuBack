package com.semillero.ubuntu.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InversionDTO {

    private Long id_inver;
    private Long monto;
    private Long cuotas;
    private Long retorno;
    private Long ganancias;
    private Long montomensual;
    private Long cuotasFaltantes;
    private LocalDate fechaCreacion;
    private String riesgo;
    private Long costo;
    private String microEmprendimiento;
    private Boolean estado;

}
