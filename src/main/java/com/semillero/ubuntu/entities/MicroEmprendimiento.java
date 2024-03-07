package com.semillero.ubuntu.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Entity
public class MicroEmprendimiento {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMasInformacion() {
        return masInformacion;
    }

    public void setMasInformacion(String masInformacion) {
        this.masInformacion = masInformacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getSubRubro() {
        return subRubro;
    }

    public void setSubRubro(String subRubro) {
        this.subRubro = subRubro;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    public boolean isGestionado() {
        return gestionado;
    }

    public void setGestionado(boolean gestionado) {
        this.gestionado = gestionado;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public String getMensajeContacto() {
        return mensajeContacto;
    }

    public void setMensajeContacto(String mensajeContacto) {
        this.mensajeContacto = mensajeContacto;
    }
}
