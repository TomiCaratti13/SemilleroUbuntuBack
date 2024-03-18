package com.semillero.ubuntu.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Entity
@Data
public class MicroEmprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String masInformacion;
    private String pais;
    private String provincia;
    private String ciudad;
    private String rubro;
    private String subRubro;
    private boolean deleted;
    private boolean gestionado;
    @OneToOne
    private Imagen imagen;
    private String mensajeContacto;

}