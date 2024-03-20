package com.semillero.ubuntu.services.impl;

import com.cloudinary.Cloudinary;
import com.semillero.ubuntu.dtos.ImagenDTO;
import com.semillero.ubuntu.entities.Imagen;
import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.repositories.ImagenRepositorio;
import com.semillero.ubuntu.repositories.PublicacionRespository;
import com.semillero.ubuntu.services.cargaImagenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class cargaImagenImpl implements cargaImagenService {


    @Autowired
    private ImagenRepositorio imagenRepositorio;

    @Autowired
    private PublicacionRespository publicacionRepositorio;

    @Autowired
    private Cloudinary cloudinary;

    @Transactional        ////CRUD PARA PUBLICACION
    @Override
    public ResponseEntity<?> cargarImagenPublicacion(Long id,@RequestParam("imagenes") List<MultipartFile> imagenes) {
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);
        if (respuesta.isPresent()) {
            try {
                Publicacion publicacion = respuesta.get();
                if (!imagenes.isEmpty()) {
                    List<String> urls = new ArrayList<>();
                    for (MultipartFile imagen : imagenes) {
                        String imagenId = UUID.randomUUID().toString();
                        Map<String, Object> respuestaDeCarga = cloudinary.uploader()
                                .upload(imagen.getBytes(), Map.of("public_id", imagenId));
                        String url = respuestaDeCarga.get("url").toString();
                        urls.add(url);
                    }

                    List<Imagen> nuevasImagenes = new ArrayList<>();
                    for (String url : urls) {
                        Imagen nuevaImagen = new Imagen();
                        nuevaImagen.setCloudinaryUrl(url);
                        nuevaImagen.setDadaDeAlta(true);
                        nuevasImagenes.add(nuevaImagen);
                    }
                    imagenRepositorio.saveAll(nuevasImagenes);
                    publicacion.setImagenes(nuevasImagenes);
                    publicacionRepositorio.save(publicacion);

                    return ResponseEntity.ok(Map.of(
                            "message", "Imágenes cargadas exitosamente",
                            "publicacion", publicacion));
                }
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
    @Transactional
    @Override
    public ResponseEntity<?> modificarImagenPublicacion(Long id, @RequestParam("imagenes") List<MultipartFile> nuevasImagenes) {
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);
        if (respuesta.isPresent()) {
            try {
                Publicacion publicacion = respuesta.get();
                List<Imagen> imagenesExistentes = publicacion.getImagenes();
                List<String> urls = new ArrayList<>();

                for (int i = 0; i < nuevasImagenes.size(); i++) {
                    MultipartFile nuevaImagen = nuevasImagenes.get(i);

                    String imagenId = UUID.randomUUID().toString();
                    Map<String, Object> respuestaDeCarga = cloudinary.uploader()
                            .upload(nuevaImagen.getBytes(), Map.of("public_id", imagenId));
                    String nuevaUrl = respuestaDeCarga.get("url").toString();

                    if (i < imagenesExistentes.size()) {
                        // La imagen ya existe, mantener la URL existente
                        Imagen imagenExistente = imagenesExistentes.get(i);
                        imagenExistente.setCloudinaryUrl(nuevaUrl);
                        imagenExistente.setDadaDeAlta(true);
                    } else {
                        // La imagen es nueva, agregar a la lista de imágenes existentes
                        Imagen nuevaImagenEntidad = new Imagen();
                        nuevaImagenEntidad.setCloudinaryUrl(nuevaUrl);
                        nuevaImagenEntidad.setDadaDeAlta(true);
                        imagenesExistentes.add(nuevaImagenEntidad);
                    }
                    urls.add(nuevaUrl);
                }
                imagenRepositorio.saveAll(imagenesExistentes);
                publicacion.setImagenes(imagenesExistentes);
                publicacionRepositorio.save(publicacion);
                List<ImagenDTO> imagenDTOs = convertirAImagenDTO(publicacion.getImagenes());
                return ResponseEntity.ok(Map.of(
                        "message", "Imágenes modificadas exitosamente",
                        "imagenes", imagenDTOs));
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<?> darDeAltaODeBajaImagen(Long imagenId) {
        Optional<Imagen> optionalImagen = imagenRepositorio.findById(imagenId);
        if (optionalImagen.isPresent()) {
            Imagen imagen = optionalImagen.get();
            boolean dadaDeAlta = imagen.getDadaDeAlta();
                imagen.setDadaDeAlta(!dadaDeAlta);
            imagenRepositorio.save(imagen);

                if (!dadaDeAlta) {
                    return ResponseEntity.ok("Imagen dada de alta exitosamente");
                } else {
                    return ResponseEntity.ok("Imagen dada de baja exitosamente");
                }
            }

        return ResponseEntity.notFound().build();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
    //////CRUD PARA MICROEMPRENDIMIENTO
    @Override
    public List<ImagenDTO> convertirAImagenDTO(List<Imagen> imagenes) {
        List<ImagenDTO> imagenDTOs = new ArrayList<>();
        for (Imagen imagen : imagenes) {
            ImagenDTO imagenDTO = new ImagenDTO();
            imagenDTO.setId(imagen.getId());
            imagenDTO.setCloudinaryUrl(imagen.getCloudinaryUrl());
            imagenDTO.setDadaDeAlta(imagen.getDadaDeAlta());
            imagenDTOs.add(imagenDTO);
        }
        return imagenDTOs;
    }


}





