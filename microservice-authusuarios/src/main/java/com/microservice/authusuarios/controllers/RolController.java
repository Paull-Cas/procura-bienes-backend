package com.microservice.authusuarios.controllers;

import com.microservice.authusuarios.dto.request.RolPermisoRequest;
import com.microservice.authusuarios.dto.request.RolRequest;
import com.microservice.authusuarios.dto.response.MensajeResponse;
import com.microservice.authusuarios.services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @PostMapping("/guardar")
    public ResponseEntity<?> create(@RequestBody RolPermisoRequest rolPermisoRequest) {
        try {
            rolService.saveRol(rolPermisoRequest.getRolRequest(), rolPermisoRequest.getPermisosRequest());
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Guardado correctamente")
                            .object(null)
                            .build()
                    , HttpStatus.CREATED
            );
        }
        catch (Exception e) {
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
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("")
                            .object(rolService.getAllRols())
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al guardar")
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RolPermisoRequest rolPermisoRequest) {
        try {
            rolService.updateRol(id, rolPermisoRequest.getRolRequest(), rolPermisoRequest.getPermisosRequest());
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al guardar")
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            rolService.deleteRol(id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al eliminar")
                            .object(null)
                            .build()
                    , HttpStatus.EXPECTATION_FAILED
            );
        }
    }
}
