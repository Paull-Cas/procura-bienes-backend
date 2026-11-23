package com.microservice.requerimientos.bienes.controllers;



import com.microservice.requerimientos.bienes.dto.RequerimientoDTO;
import com.microservice.requerimientos.bienes.dto.request.RequerimientoConBienesRequest;
import com.microservice.requerimientos.bienes.dto.request.RequerimientoRequest;
import com.microservice.requerimientos.bienes.dto.response.MensajeResponse;
import com.microservice.requerimientos.bienes.service.IRequerimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requerimiento")
public class    RequerimientoController {

    @Autowired
    IRequerimientoService requerimientoService;

    @PostMapping("/guardar")
    public ResponseEntity<?> createConBienes(@RequestBody RequerimientoConBienesRequest requerimientoConBienes){
        try {
            List<String> titulos = requerimientoService.save(requerimientoConBienes);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    MensajeResponse.builder()
                            .mensaje("Requerimientos creados")
                            .object(titulos)
                            .build()
            );
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(
                    MensajeResponse.builder()
                            .mensaje("Error: " + ex.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> readAll(@RequestParam Long idUsuario){
        try{
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(requerimientoService.getAll(idUsuario))
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (RuntimeException RT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(RT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id){
        RequerimientoDTO requerimientoByID = requerimientoService.getById(id);
        if (requerimientoByID != null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(requerimientoByID)
                            .build()
                    , HttpStatus.OK
            );
        }
        else{
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
    }

    @GetMapping("/por-titulo/{titulo}")
    public ResponseEntity<?> readByTitulo(@PathVariable String titulo){
        try {
            return new ResponseEntity<>(requerimientoService.getByTitulo(titulo), HttpStatus.OK);
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody RequerimientoRequest requerimientoDTO, @PathVariable Long id){
        try {
            requerimientoService.update(requerimientoDTO, id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Actualizado correctamente")
                            .object(requerimientoDTO.getTitulo())
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (RuntimeException exRT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exRT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            requerimientoService.deleteById(id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Eliminado correctamente")
                            .object(id)
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (RuntimeException RT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(RT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }
}
