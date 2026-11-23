package com.microservice.authusuarios.controllers;

import com.microservice.authusuarios.dto.response.MensajeResponse;
import com.microservice.authusuarios.services.IRolPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/rolesypermisos")
public class RolPermisoController {

    @Autowired
    private IRolPermisoService rolPermisoService;

    @GetMapping("/todos")
    public ResponseEntity<?> readAllRolPermisos() {
        try {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta exitosa")
                            .object(rolPermisoService.getPermisosPorRol())
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al consultar")
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }
}
