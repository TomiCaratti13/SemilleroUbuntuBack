package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepositorio  extends JpaRepository<Imagen,Long> {

}
