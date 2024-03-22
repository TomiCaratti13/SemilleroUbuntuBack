package com.semillero.ubuntu.dtos.mapper;

import com.semillero.ubuntu.dtos.ContactoDto;
import com.semillero.ubuntu.entities.Contacto;

public class DtoMapperContacto {
    private Contacto contacto;

    private DtoMapperContacto(){

    }
    public static DtoMapperContacto getInstance(){
        return new DtoMapperContacto();
    }

    public DtoMapperContacto setContacto(Contacto contacto){
        this.contacto = contacto;
        return this;
    }
    public ContactoDto build(){
        ContactoDto contactoDto = new ContactoDto();
        contactoDto.setId(contacto.getId());
        contactoDto.setFechaCreacion(contacto.getFechaCreacion());
        contactoDto.setDescripcion(contacto.getDescripcion());
        contactoDto.setUsuarioSolicitante(contacto.getUsuarioSolicitante());
        contactoDto.setMicroemprendimiento(contacto.getMicroEmprendimiento().getNombre());
        contactoDto.setGestionado(contacto.isGestionado());
        return contactoDto;
    }
}
