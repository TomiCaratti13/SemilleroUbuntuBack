package com.semillero.ubuntu.dtos;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculoDto {
    private Long costo;
    private Long total;
    private Long Cuotas;
    private Double tasa;
    private Long retorno;
    private Long ganancias;

}
