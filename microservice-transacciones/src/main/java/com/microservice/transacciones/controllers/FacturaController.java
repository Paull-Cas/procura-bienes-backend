package com.microservice.transacciones.controllers;

import com.microservice.transacciones.dto.request.FacturaRequest;
import com.microservice.transacciones.dto.response.FacturaResponse;
import com.microservice.transacciones.dto.response.MessageResponse;
import com.microservice.transacciones.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    @PostMapping("/guardar")
    public ResponseEntity<?> create(@RequestBody FacturaRequest facturarequest) {
        try {
            facturaService.save(facturarequest);
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
        catch (RuntimeException exRT){
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje(exRT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> readAll(@RequestParam Long idUsuario) {
        List<FacturaResponse> getList = facturaService.getAll(idUsuario);
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
                            .mensaje("No hay Registros de Facturas")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        FacturaResponse facturaByID = facturaService.getById(id);
        if (facturaByID != null){
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(facturaByID)
                            .build()
                    , HttpStatus.OK
            );
        }
        else{
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("No hay Registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody FacturaRequest facturarequest, @PathVariable Long id) {
        try {
            facturaService.update(facturarequest, id);
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("Actualizado correctamente")
                            .object(facturarequest)
                            .build()
                    , HttpStatus.OK
            );
        } catch (RuntimeException exRT) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
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
            facturaService.deleteById(id);
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje("Eliminado correctamente")
                            .object(id)
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (RuntimeException exRT){
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .mensaje(exRT.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
    }
}
