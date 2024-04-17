package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.entities.Pregunta;
import com.semillero.ubuntu.entities.Respuesta;
import com.semillero.ubuntu.services.impl.PreguntaServiceImpl;
import com.semillero.ubuntu.services.impl.RespuestaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pregunta")
@CrossOrigin(origins = "*")
public class PreguntaController {

    @Autowired
    private PreguntaServiceImpl service;
    @Autowired
    private RespuestaServiceImpl serviceResp;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pregunta pregunta){

        Respuesta resp = pregunta.getRespuesta();//RESPUESTA DE LA ENTIDAD PREGUNTA
        if(resp.getPreguntas() != null){
            List<Pregunta> preguntas = resp.getPreguntas();

            for (Pregunta preg: preguntas  ) {
                if(preg.getRespuesta()!= null){

                    serviceResp.save(preg.getRespuesta());
                }
            }
//            List<Respuesta> respuestasDb= resp.getPreguntas().stream().map(p -> serviceResp.save(p.getRespuesta())).collect(Collectors.toList());//GUARDO LAS RESPUESTA DE CADA PREGUNTA
            if(resp.getPreguntas()!= null){
                List<Pregunta> preguntasDb=resp.getPreguntas().stream().map(p -> service.save(p)).collect(Collectors.toList());//GUARDO LAS PREGUNTAS DE CADA RESPUESTA
                resp.setPreguntas(preguntasDb);
            }



        }



        serviceResp.save(resp);
        System.out.println(resp);
        service.save(pregunta);

        return ResponseEntity.ok("bien");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        return ResponseEntity.ok(service.findById(id));

    }
}
