package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepositorio extends JpaRepository<Pais, Integer> {
}
