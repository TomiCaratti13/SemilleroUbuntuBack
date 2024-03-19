package com.semillero.ubuntu.entities;

import com.semillero.ubuntu.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generar el ID automáticamente por la base de datos
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Boolean deleted;
    @Enumerated(EnumType.STRING)
    private Rol role;
    private String telefono;

}
