package com.semillero.ubuntu.dtos.mapper;

import com.semillero.ubuntu.dtos.PublicacionDto;
import com.semillero.ubuntu.entities.Publicacion;

public class DtoMapperPublicacion {

    private Publicacion publicacion;

    private DtoMapperPublicacion() {
    }

    public static DtoMapperPublicacion getInstance() {
        return  new DtoMapperPublicacion();
    }

    public DtoMapperPublicacion setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
        return this;
    }
    public PublicacionDto build(){
        if (publicacion==null){
            throw new RuntimeException();
        }
        PublicacionDto publicacionDto = new PublicacionDto();
        publicacionDto.setId(publicacion.getId());
        publicacionDto.setTitulo(publicacion.getTitulo());
        publicacionDto.setDescripcion(publicacion.getDescripcion());
        publicacionDto.setVisualizaciones(publicacion.getVisualizaciones());
        publicacionDto.setFechaCreacion(publicacion.getFechaCreacion());
        publicacionDto.setImagenes(publicacion.getImagenes());
        return publicacionDto;
    }

}
