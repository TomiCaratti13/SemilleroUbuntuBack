package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.dtos.DtoRiesgo;
import com.semillero.ubuntu.entities.Riesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiesgoRepository extends JpaRepository<Riesgo, Long> {

}
