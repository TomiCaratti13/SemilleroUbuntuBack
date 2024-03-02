package com.semillero.ubuntu.services.impl;

import com.semillero.ubuntu.entities.Pais;
import com.semillero.ubuntu.entities.Provincia;
import com.semillero.ubuntu.exceptions.ResourceNotFoundException;
import com.semillero.ubuntu.repositories.PaisRepositorio;
import com.semillero.ubuntu.repositories.ProvinciaRepositorio;
import com.semillero.ubuntu.services.PaisService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisServiceImpl implements PaisService {

    @Autowired
    private PaisRepositorio paisRepositorio;

    @Autowired
    private ProvinciaRepositorio provinciaRepositorio;

    @Override
    public List<Pais> listarPaises() {
        return paisRepositorio.findAll();
    }

    @Override
    public Pais buscarPaisPorId(Integer id) {
        return paisRepositorio.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarPais(Pais pais) {
        paisRepositorio.save(pais);
    }

    @Override
    @Transactional
    public void eliminarPaisPorId(Integer id) {
        paisRepositorio.deleteById(id);
    }

    @Override
    public List<Provincia> listarProvinciasPorPais(Integer id) {
        Optional<Pais> paisOptional = paisRepositorio.findById(id);
        if (paisOptional.isPresent()) {
            Pais pais = paisOptional.get();
            return pais.getProvincias();
        } else {
            throw new ResourceNotFoundException("No se encontro el pais con ID: " + id);
        }
    }
}
