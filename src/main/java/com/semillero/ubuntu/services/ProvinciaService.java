package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Provincia;

import java.util.List;

public interface ProvinciaService {
    List<Provincia> listarProvincias();

    Provincia buscarProvinciaPorId(Integer id);

    void guardarProvincia(Provincia provincia);

    void eliminarProvinciaPorId(Integer id);
}
