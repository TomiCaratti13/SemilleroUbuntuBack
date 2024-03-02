package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepositorio extends JpaRepository<Provincia, Integer> {
}
