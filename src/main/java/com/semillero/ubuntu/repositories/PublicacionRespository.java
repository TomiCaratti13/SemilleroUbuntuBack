package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRespository extends JpaRepository<Publicacion,Long> {

    List<Publicacion> findByDeletedFalse();

}
