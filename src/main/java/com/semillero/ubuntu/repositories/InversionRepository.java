package com.semillero.ubuntu.repositories;

import com.semillero.ubuntu.entities.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InversionRepository extends JpaRepository<Inversion,Long> {

}
