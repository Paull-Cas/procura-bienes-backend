package com.microservice.requerimientos.bienes.controllers;

import com.microservice.requerimientos.bienes.dto.BienDTO;
import com.microservice.requerimientos.bienes.dto.response.MensajeResponse;
import com.microservice.requerimientos.bienes.service.IBienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bien")
public class BienController {

    @Autowired
    private IBienService bienService;

    @PostMapping("/guardar")
    public ResponseEntity<?> create(@RequestBody BienDTO bienDTO) {
        try {
            bienService.save(bienDTO);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Guardado correctamente")
                            .object(bienDTO)
                            .build()
                    , HttpStatus.CREATED
            );
        }
        catch (DataAccessException exDT){
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
    public ResponseEntity<?> readAll() {
        List<BienDTO> getList = bienService.getAll();
        if (getList != null && !getList.isEmpty()){
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
                            .mensaje("No hay Registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        BienDTO bienByID = bienService.getById(id);
        if (bienByID != null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(bienByID)
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
    public ResponseEntity<?> update(@RequestBody BienDTO bienDTO, @PathVariable Long id) {
        try {
            bienService.update(bienDTO, id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Actualizado correctamente")
                            .object(bienDTO)
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
            bienService.deleteById(id);
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
