package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InversionRepository extends JpaRepository<Inversion,Long> {


    @Query( value = "SELECT * FROM inversiones i WHERE i.usuario_id = :id",nativeQuery = true)
    List<Inversion> obtenerMisInversiones(@Param("id") Long id);

}
