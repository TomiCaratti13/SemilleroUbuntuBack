package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.dtos.Cant_Mic_RubroDTO;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MicroEmprendimientoRepository extends JpaRepository<MicroEmprendimiento, Long> {

    @Query("SELECT a from MicroEmprendimiento a WHERE a.nombre LIKE %:nombre%")
    public List<MicroEmprendimiento> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT m FROM MicroEmprendimiento m WHERE m.fechaCreacion BETWEEN :fechaInicio AND :fechaFin")
    List<MicroEmprendimiento> buscarPorRangoDeFecha(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    @Query( value = "SELECT r.nombre AS categoria, COUNT(m.id) AS sum_micro " +
            "FROM micro_emprendimiento m JOIN rubro r ON m.rubro_id = r.id GROUP BY m.rubro_id",nativeQuery = true)
    List<Object[]> obtenerCantidadPorRubro();
}
