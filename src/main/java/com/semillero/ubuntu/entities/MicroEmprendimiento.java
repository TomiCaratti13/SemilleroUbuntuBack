package com.semillero.ubuntu.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

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
   /* @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "microemprendimiento_id")
    @Size(min = 1, max = 3)
    private List<Imagen> imagenes;*/
    private String mensajeContacto;

}