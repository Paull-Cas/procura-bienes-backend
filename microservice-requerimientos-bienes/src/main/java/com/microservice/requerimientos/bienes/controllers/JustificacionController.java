package com.microservice.requerimientos.bienes.controllers;

import com.microservice.requerimientos.bienes.dto.JustificacionDTO;
import com.microservice.requerimientos.bienes.dto.response.MensajeResponse;
import com.microservice.requerimientos.bienes.service.IJustificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/justificacion")
public class JustificacionController {

    @Autowired
    private IJustificacionService justificacionService;

    @PostMapping("/guardar")
    public ResponseEntity<?> create(@RequestBody JustificacionDTO dto) {
        try {
            justificacionService.save(dto);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Guardado correctamente")
                            .object(dto)
                            .build()
                    , HttpStatus.CREATED
            );
        }
        catch (DataAccessException exDT) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al guardar")
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> readAll(){
        List<JustificacionDTO> getList = justificacionService.getAll();

        if(getList != null && !getList.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(getList)
                            .build()
                    , HttpStatus.OK
            );
        }
        else {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        JustificacionDTO justificacionByID = justificacionService.getById(id);
        if (justificacionByID != null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(justificacionByID)
                            .build()
                    , HttpStatus.OK
            );
        }
        else{
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay Registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody JustificacionDTO justificacionDTO, @PathVariable Long id) {
        try {
            justificacionService.update(justificacionDTO, id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Actualizado correctamente")
                            .object(justificacionDTO)
                            .build()
                    , HttpStatus.OK
            );
        } catch (RuntimeException exRT) {
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
        try {
            justificacionService.deleteById(id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Eliminado correctamente")
                            .object(id)
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
}
