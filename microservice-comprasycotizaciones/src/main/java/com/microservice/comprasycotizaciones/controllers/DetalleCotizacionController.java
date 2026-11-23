package com.microservice.comprasycotizaciones.controllers;

import com.microservice.comprasycotizaciones.dto.request.DetalleCotizacionRequest;
import com.microservice.comprasycotizaciones.dto.request.EvaluacionDetallCotizacionRequest;
import com.microservice.comprasycotizaciones.dto.response.MensajeResponse;
import com.microservice.comprasycotizaciones.service.IDetalleCotizacionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/detalle-cotizacion")
@Slf4j
public class DetalleCotizacionController {

    @Autowired
    private IDetalleCotizacionService detalleCotizacionService;

    @GetMapping("/todos")
    public ResponseEntity<?> readAllByCotizacion(@RequestParam Long idCotizacion) {
        try {
            return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Consulta exitosa")
                                .object(detalleCotizacionService.getAllByCotizacion(idCotizacion))
                                .build()
                        , HttpStatus.OK
                    );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(detalleCotizacionService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/actualizar/{idCotizacion}")
    public ResponseEntity<?> update(@PathVariable Long idCotizacion,
                                    @RequestBody List<DetalleCotizacionRequest> listaItems) {
        try {
            detalleCotizacionService.update(idCotizacion, listaItems);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Actualización exitosa")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            detalleCotizacionService.deleteById(id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Eliminación Exitosa")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/enviar-evaluacion/{id}")
    public ResponseEntity<?> evaluarDetalleCotizacion(@PathVariable Long id, @RequestBody List<EvaluacionDetallCotizacionRequest> detallesCotizacionRequest) {
        try {
            detalleCotizacionService.evaluarDetalleCotizacion(id, detallesCotizacionRequest);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Actualización exitosa")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }
}