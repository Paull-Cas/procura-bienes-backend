package com.microservice.comprasycotizaciones.controllers;

import com.microservice.comprasycotizaciones.dto.request.ProcuraRequest;
import com.microservice.comprasycotizaciones.dto.response.MensajeResponse;
import com.microservice.comprasycotizaciones.service.IProcuraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/procura")
public class ProcuraController {

    @Autowired private IProcuraService procuraService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody ProcuraRequest procuraRequest) {
        try {
            procuraService.save(procuraRequest);
            return new ResponseEntity<>(procuraRequest, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> readAll(@RequestParam Long idUsuario) {
        try {
            return new ResponseEntity<>(procuraService.getAll(idUsuario), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/no-cotizadas")
    public ResponseEntity<?> readAllNoCotizadas(@RequestParam Long idUsuario) {
        try {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta exitosa")
                            .object(procuraService.getAllNoCotizadas(idUsuario))
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/comparativo")
    public ResponseEntity<?> readComparativo(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta exitosa")
                            .object(procuraService.getComparativo(id))
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(procuraService.getById(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProcuraRequest procuraRequest) {
        try{
            procuraService.update(procuraRequest, id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            procuraService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
