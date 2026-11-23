package com.microservice.authusuarios.controllers;

import com.microservice.authusuarios.dto.response.MensajeResponse;
import com.microservice.authusuarios.services.IModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/modulos")
public class ModuloController {

    @Autowired
    private IModuloService moduloService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAll() {
        List<?> modulos = moduloService.getAll();

        if (modulos != null && !modulos.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(modulos)
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

    @GetMapping("/permitidos")
    public ResponseEntity<?> obtenerModulosPermitidos() {
        List<String> permisos = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        List<?> modulos = moduloService.obtenerModulosPorPermisos(permisos);

        if (modulos != null && !modulos.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(modulos)
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
}
