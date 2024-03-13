package com.semillero.ubuntu.entities;

import com.semillero.ubuntu.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generar el ID automáticamente por la base de datos
    private Long id;
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Password;
    private Boolean Deleted;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private String Telefono;

}
