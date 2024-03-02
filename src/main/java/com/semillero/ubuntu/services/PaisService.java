package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Pais;
import com.semillero.ubuntu.entities.Provincia;

import java.util.List;

public interface PaisService {
    List<Pais> listarPaises();

    Pais buscarPaisPorId(Integer id);

    void guardarPais(Pais pais);

    void eliminarPaisPorId(Integer id);

    List<Provincia> listarProvinciasPorPais(Integer id);
}
