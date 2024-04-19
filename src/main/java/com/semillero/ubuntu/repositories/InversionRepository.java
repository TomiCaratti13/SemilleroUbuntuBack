package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Inversion;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Repository
public interface InversionRepository extends JpaRepository<Inversion,Long> {


    @Query( value = "SELECT * FROM inversiones i WHERE i.usuario_id = id",nativeQuery = true)
    List<Inversion> obtenerMisInversiones(@Param ("id") Long id);
}
