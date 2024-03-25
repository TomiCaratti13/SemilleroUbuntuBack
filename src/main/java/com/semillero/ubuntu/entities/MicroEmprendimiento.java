package com.semillero.ubuntu.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    private LocalDate fechaCreacion;
    private boolean deleted;
    private boolean gestionado;
    //@OneToOne
    //private Imagen imagen;
    @OneToMany
    private List<Contacto> contactos = new ArrayList<>();

    public void addContactos(Contacto contacto) {
        this.contactos.add(contacto);
    }

    public void setFechaCreacionNow() {
        this.fechaCreacion = LocalDate.now();
    }
}