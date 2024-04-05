package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepository extends JpaRepository<Pregunta,Long> {
}
