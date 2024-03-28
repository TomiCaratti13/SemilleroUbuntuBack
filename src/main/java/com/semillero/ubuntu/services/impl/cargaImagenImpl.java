package com.semillero.ubuntu.services.impl;

import com.cloudinary.Cloudinary;
import com.semillero.ubuntu.dtos.ImagenDTO;
import com.semillero.ubuntu.entities.Imagen;
import com.semillero.ubuntu.entities.MicroEmprendimiento;
import com.semillero.ubuntu.entities.Publicacion;
import com.semillero.ubuntu.entities.Rubro;
import com.semillero.ubuntu.repositories.ImagenRepositorio;
import com.semillero.ubuntu.repositories.MicroEmprendimientoRepository;
import com.semillero.ubuntu.repositories.PublicacionRespository;
import com.semillero.ubuntu.repositories.RubroRepository;
import com.semillero.ubuntu.services.cargaImagenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    private MicroEmprendimientoRepository microEmprendimientoRepositorio;
    @Autowired
    private RubroRepository rubroRepositorio;
    @Autowired
    private Cloudinary cloudinary;

    @Transactional        ////CRUD PARA PUBLICACION
    @Override
    public ResponseEntity<?> cargarImagenPublicacion(@RequestParam Long id,@RequestParam("imagenes") List<MultipartFile> imagenes) {
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
                    List<ImagenDTO> imagenDTOs = convertirAImagenDTO(publicacion.getImagenes());
                    return ResponseEntity.ok(imagenDTOs);
                }
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
    @Transactional
    @Override
    public ResponseEntity<?> modificarImagenPublicacion(@RequestParam Long id, @RequestParam("imagenes") List<MultipartFile> nuevasImagenes) {
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);
        if (respuesta.isPresent()) {
            try {
                Publicacion publicacion = respuesta.get();
                List<Imagen> imagenesExistentes = publicacion.getImagenes();
                List<String> urls = new ArrayList<>();

                imagenesExistentes.clear();


                for (int i = 0; i < nuevasImagenes.size(); i++) {
                    MultipartFile nuevaImagen = nuevasImagenes.get(i);

                    String imagenId = UUID.randomUUID().toString();
                    Map<String, Object> respuestaDeCarga = cloudinary.uploader()
                            .upload(nuevaImagen.getBytes(), Map.of("public_id", imagenId));
                    String nuevaUrl = respuestaDeCarga.get("url").toString();

                    Imagen nuevaImagenEntidad = new Imagen();
                    nuevaImagenEntidad.setCloudinaryUrl(nuevaUrl);
                    nuevaImagenEntidad.setDadaDeAlta(true);
                    imagenesExistentes.add(nuevaImagenEntidad);
                    urls.add(nuevaUrl);
                }
                imagenRepositorio.saveAll(imagenesExistentes);
                publicacion.setImagenes(imagenesExistentes);
                publicacionRepositorio.save(publicacion);
                List<ImagenDTO> imagenDTOs = convertirAImagenDTO(publicacion.getImagenes());
                return ResponseEntity.ok(imagenDTOs);
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

    @Transactional        ////CRUD PARA PUBLICACION

    public ResponseEntity<?> cargarImagenMicroemprendimiento(@RequestParam Long id,@RequestParam("imagenes") List<MultipartFile> imagenes) {
        Optional<MicroEmprendimiento> respuesta = microEmprendimientoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            try {
                MicroEmprendimiento microEm = respuesta.get();
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
                    microEm.setImagenes(nuevasImagenes);
                    microEmprendimientoRepositorio.save(microEm);
                    List<ImagenDTO> imagenDTOS = convertirAImagenDTO(microEm.getImagenes());
                    return ResponseEntity.ok(imagenDTOS);
                }
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    @Transactional
    public ResponseEntity<?> modificarImagenMicroemprendimiento(@RequestParam Long id, @RequestParam("imagenes") List<MultipartFile> nuevasImagenes) {
        Optional<MicroEmprendimiento> respuesta = microEmprendimientoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            try {
                MicroEmprendimiento microEm = respuesta.get();
                List<Imagen> imagenesExistentes = microEm.getImagenes();
                List<String> urls = new ArrayList<>();


                imagenesExistentes.clear();

                for (int i = 0; i < nuevasImagenes.size(); i++) {
                    MultipartFile nuevaImagen = nuevasImagenes.get(i);

                    String imagenId = UUID.randomUUID().toString();
                    Map<String, Object> respuestaDeCarga = cloudinary.uploader()
                            .upload(nuevaImagen.getBytes(), Map.of("public_id", imagenId));
                    String nuevaUrl = respuestaDeCarga.get("url").toString();


                    Imagen nuevaImagenEntidad = new Imagen();
                    nuevaImagenEntidad.setCloudinaryUrl(nuevaUrl);
                    nuevaImagenEntidad.setDadaDeAlta(true);
                    imagenesExistentes.add(nuevaImagenEntidad);
                    urls.add(nuevaUrl);
                }
                imagenRepositorio.saveAll(imagenesExistentes);
                microEm.setImagenes(imagenesExistentes);
                microEmprendimientoRepositorio.save(microEm);
                List<ImagenDTO> imagenDTOs = convertirAImagenDTO(microEm.getImagenes());
                return ResponseEntity.ok(imagenDTOs);
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

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
    @Override
    public ResponseEntity<?> cargarImagenRubro(@RequestParam Long id, @RequestParam("imagen") MultipartFile imagen) {

        Optional<Rubro> respuesta = rubroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            try {
                Rubro rubro = respuesta.get();

                String imagenId = UUID.randomUUID().toString();
                Map<String, Object> respuestaDeCarga = cloudinary.uploader()
                        .upload(imagen.getBytes(), Map.of("public_id", imagenId));
                String nuevaUrl = respuestaDeCarga.get("url").toString();

                Imagen nuevaImagen = new Imagen();
                nuevaImagen.setCloudinaryUrl(nuevaUrl);
                nuevaImagen.setDadaDeAlta(true);
                imagenRepositorio.save(nuevaImagen);

                rubro.setImagen(nuevaImagen);
                rubroRepositorio.save(rubro);

                ImagenDTO imagenDTO = new ImagenDTO();
                imagenDTO.setId(nuevaImagen.getId());
                imagenDTO.setCloudinaryUrl(nuevaImagen.getCloudinaryUrl());
                imagenDTO.setDadaDeAlta(nuevaImagen.getDadaDeAlta());


                return ResponseEntity.ok(imagenDTO);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Error al cargar la imagen: "+e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Error, no se encontro rubro con ese id");
        }
    }

}





