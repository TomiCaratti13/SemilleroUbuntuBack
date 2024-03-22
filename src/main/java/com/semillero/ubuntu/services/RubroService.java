package com.semillero.ubuntu.services;

import com.semillero.ubuntu.entities.Rubro;
import com.semillero.ubuntu.exceptions.ExceptionCreados;

import java.util.List;

public interface RubroService{

    public void cargarNuevoRubro(Rubro rubro) throws ExceptionCreados;

    public List<Rubro> listarRubros() throws ExceptionCreados;

    public void validar(String nombre) throws ExceptionCreados;
}
