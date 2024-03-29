package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublicacionRespository extends JpaRepository<Publicacion,Long> {

    List<Publicacion> findByDeletedFalseOrderByFechaCreacionDesc();

    @Query( value = "SELECT p.titulo, p.descripcion, p.fecha_creacion, " +
            "p.visualizaciones FROM publicacion p " +
            "WHERE MONTH(p.fecha_creacion) = MONTH(CURRENT_DATE);",
            nativeQuery = true)
    List<Object[]> obtenerTotalVisualizaciones();

}
