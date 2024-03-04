package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Rubro;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.repositories.RubroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubroService {

    @Autowired
    private RubroRepository rubroRepository;

    @Transactional
    public void cargarNuevoRubro(Rubro rubro) throws ExceptionCreados{

        validar(rubro.getNombre());

        rubroRepository.save(rubro);

    }


    public List<Rubro> listarRubros() throws ExceptionCreados {

        List<Rubro> respuesta = rubroRepository.findAll();
        if (respuesta.isEmpty()) {

            throw new ExceptionCreados("No se encontraron rubros");

        }
        return respuesta;

    }

    public void validar(String nombre) throws ExceptionCreados {

        if (nombre == null || nombre.isEmpty() || nombre.contains("  ")) {
            throw new ExceptionCreados("Debe tener un nombre valido");
        }
    }








}
