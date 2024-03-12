package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.MicroEmprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroEmprendimientoRepository extends JpaRepository<MicroEmprendimiento, String> {

    @Query("SELECT a from MicroEmprendimiento a WHERE a.nombre LIKE :nombre")
    public List<MicroEmprendimiento> buscarPorNombre(@Param("nombre") String nombre);
}
