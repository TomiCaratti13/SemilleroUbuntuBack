package com.semillero.ubuntu.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.semillero.ubuntu.entities.Imagen;
import lombok.Data;

import java.io.Serializable;

@Data
public class MicroEmprendimientoRequest implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("descripcion")
    private String descripcion;
    @JsonProperty("masInformacion")
    private String masInformacion;
    @JsonProperty("pais")
    private String pais;
    @JsonProperty("provincia")
    private String provincia;
    @JsonProperty("ciudad")
    private String ciudad;
    @JsonProperty("rubro")
    private String rubro;
    @JsonProperty("subrubro")
    private String subRubro;
    @JsonProperty("imagen")
    private Imagen imagen;
    @JsonProperty("mensajeContacto")
    private String mensajeContacto;
    @JsonProperty("deleted")
    private boolean deleted;
    @JsonProperty("gestionado")
    private boolean gestionado;


}
