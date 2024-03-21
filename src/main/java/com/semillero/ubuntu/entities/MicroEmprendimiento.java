package com.semillero.ubuntu.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
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
    private String pais;
    private String provincia;
    private String ciudad;
    private String rubro;
    private String subRubro;
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


}