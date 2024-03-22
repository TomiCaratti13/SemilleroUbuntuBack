package com.semillero.ubuntu.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
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
    @ManyToOne
    private Pais pais;
    @ManyToOne
    private Provincia provincia;
    private String ciudad;
    @ManyToOne
    private Rubro rubro;
    private String subRubro;

    private boolean deleted;
    private boolean gestionado;
    //@OneToOne
    //private Imagen imagen;
    @OneToMany
    private List<Contacto> contactos = new ArrayList<>();

    public void addContactos(Contacto contacto) {
        this.contactos.add(contacto);
    }

}