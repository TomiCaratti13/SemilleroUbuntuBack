package com.semillero.ubuntu.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.semillero.ubuntu.entities.Imagen;
import com.semillero.ubuntu.exceptions.ExceptionCreados;
import com.semillero.ubuntu.repositories.ImagenRepositorio;
import com.semillero.ubuntu.services.cargaImagenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class cargaImagenImpl implements cargaImagenService {

    private final Cloudinary cloudinary;

    @Autowired
    private ImagenRepositorio imagenRepository;

    @Override
    public ResponseEntity<?> cargarImagen(@RequestParam("imagen") MultipartFile imagen) throws ExceptionCreados {
        try {
            if (imagen != null) {
                String imagenId = UUID.randomUUID().toString();
                Map<String, Object> respuestaDeCarga = cloudinary.uploader()
                        .upload(imagen.getBytes(), Map.of("public_id", imagenId));
                String url = respuestaDeCarga.get("url").toString();

                Imagen nuevaImagen = new Imagen();
                nuevaImagen.setCloudinaryUrl(url);
                nuevaImagen.setDadaDeAlta(true);
                imagenRepository.save(nuevaImagen);

                return ResponseEntity.ok("Imagen cargada exitosamente");
            }
        } catch (IOException e) {
            throw new ExceptionCreados("La imagen no se pudo cargar correctamente: " + e.getMessage());
        }

        throw new ExceptionCreados("No se proporcionó ninguna imagen para cargar");
    }


    @Override
    public ResponseEntity<?> modificarImagen(@RequestParam("imagen") MultipartFile imagen) throws ExceptionCreados {
        try {
            // Obtener todas las imágenes de la base de datos (puedes ajustar esto según tu estructura)
            List<Imagen> imagenes = imagenRepository.findAll();

            // Suponiendo que solo hay una imagen en la base de datos, obtén la primera imagen
            if (!imagenes.isEmpty()) {
                Imagen imagenExistente = imagenes.get(0);

                // Generar nueva URL con Cloudinary
                String imagenIdCloudinary = UUID.randomUUID().toString();
                Map<String, Object> respuestaDeCarga = cloudinary.uploader()
                        .upload(imagen.getBytes(), Map.of("public_id", imagenIdCloudinary));
                String nuevaUrl = respuestaDeCarga.get("url").toString();

                // Actualizar la URL y marcar como dada de alta
                imagenExistente.setCloudinaryUrl(nuevaUrl);
                imagenExistente.setDadaDeAlta(true);
                imagenRepository.save(imagenExistente);

                return ResponseEntity.ok("Imagen modificada exitosamente");
            } else {
                throw new ExceptionCreados("No se encontraron imágenes en la base de datos");
            }
        } catch (Exception e) {
            throw new ExceptionCreados("La imagen no se pudo modificar correctamente: " + e.getMessage());
        }
    }





    @Override
    public ResponseEntity<?> darDeAltaODeBajaImagen() throws ExceptionCreados {
        try {
            // Obtener todas las imágenes de la base de datos (puedes ajustar esto según tu estructura)
            List<Imagen> imagenes = imagenRepository.findAll();

            // Suponiendo que solo hay una imagen en la base de datos, obtén la primera imagen
            if (!imagenes.isEmpty()) {
                Imagen imagen = imagenes.get(0);
                boolean dadaDeAlta = imagen.getDadaDeAlta(); // Obtener el estado actual

                // Invertir el estado actual (dar de baja si está alta, dar de alta si está baja)
                imagen.setDadaDeAlta(!dadaDeAlta);
                imagenRepository.save(imagen);

                if (!dadaDeAlta) {
                    return ResponseEntity.ok("Imagen dada de alta exitosamente");
                } else {
                    return ResponseEntity.ok("Imagen dada de baja exitosamente");
                }
            } else {
                throw new ExceptionCreados("No se encontraron imágenes en la base de datos");
            }
        } catch (Exception e) {
            throw new ExceptionCreados("Error al dar de alta o de baja la imagen: " + e.getMessage());
        }
    }




}
