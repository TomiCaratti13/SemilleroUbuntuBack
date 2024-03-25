package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepository extends JpaRepository<Contacto,Long> {
}
