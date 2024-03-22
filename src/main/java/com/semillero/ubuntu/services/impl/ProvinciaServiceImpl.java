package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.entities.Provincia;
import com.semillero.ubuntu.repositories.ProvinciaRepositorio;
import com.semillero.ubuntu.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    private ProvinciaRepositorio provinciaRepositorio;

    @Override
    public List<Provincia> listarProvincias() {
        return provinciaRepositorio.findAll();
    }

    @Override
    public Provincia buscarProvinciaPorId(Integer id) {
        return provinciaRepositorio.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Provincia guardarProvincia(Provincia provincia) {
       return  provinciaRepositorio.save(provincia);
    }

    @Override
    @Transactional
    public void eliminarProvinciaPorId(Integer id) {
        provinciaRepositorio.deleteById(id);
    }
}
