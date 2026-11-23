package com.microservice.authusuarios.controllers;

import com.microservice.authusuarios.dto.response.MensajeResponse;
import com.microservice.authusuarios.dto.response.PermisoResponse;
import com.microservice.authusuarios.services.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;

    @GetMapping("/todos")
    public ResponseEntity<?> readAll() {
        List<PermisoResponse> permisos = permisoService.getAllPermisos();

        if(permisos.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay permisos")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta exitosa")
                        .object(permisos)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        PermisoResponse permiso = permisoService.getById(id);
        if(permiso == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe el permiso solicitado")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("consulta exitosa")
                        .object(permiso)
                        .build()
                , HttpStatus.OK
        );
    }
}
