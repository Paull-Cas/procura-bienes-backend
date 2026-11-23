package com.microservice.requerimientos.bienes.controllers;

import com.microservice.requerimientos.bienes.dto.DetalleRequerimientoDTO;
import com.microservice.requerimientos.bienes.dto.response.MensajeResponse;
import com.microservice.requerimientos.bienes.service.IDetalleRequerimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-requerimiento")
public class DetalleRequerimientoController {

    @Autowired
    IDetalleRequerimientoService detalleService;

    @GetMapping("/todos")
    public ResponseEntity<?> readAllByRequerimiento(@RequestParam Long idRequerimiento) {
        List<DetalleRequerimientoDTO> lista = detalleService.getAllByRequerimiento(idRequerimiento);
        if (lista != null && !lista.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta exitosa")
                            .object(lista)
                            .build(),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build(),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        DetalleRequerimientoDTO dto = detalleService.getById(id);
        if (dto != null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta exitosa")
                            .object(dto)
                            .build(),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build(),
                    HttpStatus.OK
            );
        }
    }

    @PutMapping("/actualizar/{idRequerimiento}")
    public ResponseEntity<?> update(
            @RequestBody List<DetalleRequerimientoDTO> listaDTO,
            @PathVariable Long idRequerimiento) {
        try {
            detalleService.update(idRequerimiento, listaDTO);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Actualización exitosa")
                            .object(listaDTO)
                            .build(),
                    HttpStatus.OK
            );
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al actualizar: " + ex.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            detalleService.deleteById(id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Eliminado correctamente")
                            .object(id)
                            .build(),
                    HttpStatus.OK
            );
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
