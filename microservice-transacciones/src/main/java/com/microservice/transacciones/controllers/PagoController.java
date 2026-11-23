package com.microservice.transacciones.controllers;

import com.microservice.transacciones.dto.request.PagoRequest;
import com.microservice.transacciones.dto.response.MessageResponse;
import com.microservice.transacciones.dto.response.PagoResponse;
import com.microservice.transacciones.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    @Autowired
    private IPagoService pagoService;

    @PostMapping("/guardar")
    public ResponseEntity<?> create(@RequestBody PagoRequest pagoRequest) {
        try {
            pagoService.save(pagoRequest);
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("Guardado Correctamente")
                            .object(null)
                            .build()
                    , HttpStatus.CREATED
            );
        }
        catch (DataAccessException exDT){
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("Error al guardar factura")
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> readAll() {
        List<PagoResponse> getList = pagoService.getAll();
        if (getList != null && !getList.isEmpty()){
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(getList)
                            .build()
                    , HttpStatus.OK
            );
        }
        else {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("No hay Registros de Pagos")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
    }

    @GetMapping("/por-factura")
    public ResponseEntity<?> readPagosPorFactura(@RequestParam Long idFactura) {
        List<PagoResponse> getList = pagoService.getListByIdFactura(idFactura);
        if (getList != null && !getList.isEmpty()){
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(getList)
                            .build()
                    , HttpStatus.OK
            );
        }
        else {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("No hay Registros de Pagos")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
    }
}
