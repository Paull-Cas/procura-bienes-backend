package com.microservice.comprasycotizaciones.controllers;

import com.microservice.comprasycotizaciones.dto.request.DetalleCompraRequest;
import com.microservice.comprasycotizaciones.service.IDetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-compra")
public class DetalleCompraController {

    @Autowired
    private IDetalleCompraService detalleCompraService;

    @GetMapping("/todos")
    public ResponseEntity<?> readAllByCompra(@RequestParam Long idCompra) {
        try {
            return new ResponseEntity<>(detalleCompraService.getAllByCompra(idCompra), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(detalleCompraService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar/{idCompra}")
    public ResponseEntity<?> update(@PathVariable Long idCompra,
                                    @RequestBody List<DetalleCompraRequest> listaItems) {
        try {
            detalleCompraService.update(idCompra, listaItems);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            detalleCompraService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}