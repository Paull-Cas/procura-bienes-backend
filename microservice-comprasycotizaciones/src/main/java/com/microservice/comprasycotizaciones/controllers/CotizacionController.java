package com.microservice.comprasycotizaciones.controllers;

import com.microservice.comprasycotizaciones.dto.request.CotizacionConBienesRequest;
import com.microservice.comprasycotizaciones.dto.request.CotizacionRequest;
import com.microservice.comprasycotizaciones.dto.response.MensajeResponse;
import com.microservice.comprasycotizaciones.service.ICotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotizacion")
public class CotizacionController {

    @Autowired
    private ICotizacionService cotizacionService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody CotizacionConBienesRequest cotizacionConBienes) {
        try {
            String titulo = cotizacionService.save(cotizacionConBienes);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    MensajeResponse.builder()
                            .mensaje("Cotizacion guardada con exito")
                            .object(titulo)
                            .build()
            );
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> readAll(@RequestParam Long idUsuario) {
        try {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta exitosa")
                            .object(cotizacionService.getAll(idUsuario))
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
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta exitosa")
                            .object(cotizacionService.getById(id))
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

    @GetMapping("/por-procura-proveedor")
    public ResponseEntity<?> readByProveedor(@RequestParam Long idUsuario, @RequestParam Long idProcura) {
        try{
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta exitosa")
                            .object(cotizacionService.getByProcuraProveedor(idUsuario, idProcura))
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CotizacionRequest cotizacionRequest) {
        try {
            cotizacionService.update(cotizacionRequest, id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Actualizacion exitosa")
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
            cotizacionService.delete(id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Eliminación exitosa")
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
}