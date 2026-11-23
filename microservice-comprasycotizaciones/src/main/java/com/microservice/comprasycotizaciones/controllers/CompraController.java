package com.microservice.comprasycotizaciones.controllers;

import com.microservice.comprasycotizaciones.dto.request.CompraConBienesRequest;
import com.microservice.comprasycotizaciones.dto.request.CompraRequest;
import com.microservice.comprasycotizaciones.service.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    @Autowired
    private ICompraService compraService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody CompraConBienesRequest compraConBienes) {
        try {
            String titulo = compraService.save(compraConBienes);
            return new ResponseEntity<>(titulo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> readAll(@RequestParam Long idUsuario) {
        try {
            return new ResponseEntity<>(compraService.getAll(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/por-proveedor")
    public ResponseEntity<?> readByProveedor(@RequestParam Long idUsuario) {
        try {
            return new ResponseEntity<>(compraService.getByProveedor(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(compraService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/aceptar/{id}")
    public ResponseEntity<?> aceptar(@PathVariable Long id) {
        try {
            compraService.aceptar(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CompraRequest compraRequest) {
        try {
            compraService.update(compraRequest, id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            compraService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}