package com.semillero.ubuntu.entities;


import com.semillero.ubuntu.enums.NiveldeRiesgo;
import com.semillero.ubuntu.enums.Rol;
import com.semillero.ubuntu.repositories.UsuarioRepositorio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Inversiones")
public class Inversiones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "inv_min", precision = 10, scale = 2)
    private Long min_inv;
    @Column(name = "inv_Max", precision = 10, scale = 2)
    private Long max_inv;
    @Column(name = "inv_min", precision = 10, scale = 2)
    private Long tasa_de_retorno;
    @Column(name = "inv_min", precision = 10, scale = 2)
    private Long cuotas;
    @Column(name = "inv_min", precision = 10, scale = 2)
    private Long costo_de_gestion;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_de_riesgo")
    private NiveldeRiesgo niveldeRiesgo;
    @Column (length = 200)
    private String descripcion;

    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "inversion_id")
    private MicroEmprendimiento microEmprendimiento;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "inversor_id")
    private Usuario usuario_id;



}




