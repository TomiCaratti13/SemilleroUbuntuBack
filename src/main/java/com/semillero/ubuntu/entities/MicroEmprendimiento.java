package com.semillero.ubuntu.entities;




import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

@Entity
@Data
public class MicroEmprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Column(length = 300)
    private String descripcion;
    @Column(length = 300)
    private String masInformacion;
    @ManyToOne
    private Pais pais;
    @ManyToOne
    private Provincia provincia;
    private String ciudad;
    @ManyToOne
    private Rubro rubro;
    private String subRubro;

    private LocalDate fechaCreacion;

    private boolean deleted;
    private boolean gestionado;
    private String mensajeContacto;
    @OneToMany
    private List<Contacto> contactos = new ArrayList<>();
    public void addContactos(Contacto contacto) {
        this.contactos.add(contacto);
    }
    @OneToMany()
    @Size(min = 1, max = 3)
    private List<Imagen> imagenes;


    public void setFechaCreacionNow() {
        this.fechaCreacion = LocalDate.now();
    }

    @OneToMany
    private List<Inversion> inversiones;


}