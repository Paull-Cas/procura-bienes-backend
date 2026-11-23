package com.microservice.authusuarios.controllers;

import com.microservice.authusuarios.dto.request.SignUpRequest;
import com.microservice.authusuarios.dto.response.MensajeResponse;
import com.microservice.authusuarios.dto.response.UsuarioDashboardResponse;
import com.microservice.authusuarios.dto.response.UsuarioResponse;
import com.microservice.authusuarios.dto.response.UsuariosReponse;
import com.microservice.authusuarios.security.jwt.JwtUtils;
import com.microservice.authusuarios.services.IUsuarioService;
import com.microservice.authusuarios.services.UserDetailsServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class    UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UserDetailsServiceImpl usuarioService;

    @Autowired
    IUsuarioService iUsuarioService;

    @GetMapping("/datos-by-id")
    public List<UsuarioResponse> buscarUsuarios(@RequestParam List<Long> idList) {
        return usuarioService.obtenerUsuarios(idList);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> obtenerDatosDashboard() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        logger.info("email..." + email);

        UsuarioDashboardResponse response = usuarioService.obtenerDatosDashboard(email);

        if (response != null) {
            return ResponseEntity.ok(
                    MensajeResponse.builder()
                            .mensaje("Consulta exitosa")
                            .object(response)
                            .build()
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    MensajeResponse.builder()
                            .mensaje("Usuario no encontrado")
                            .object(null)
                            .build()
            );
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> findAll() {
        try {
            List<UsuariosReponse> usuarios = iUsuarioService.findAll();
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Usuarios obtenidos exitosamente")
                            .object(usuarios)
                            .build()
                    , HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener usuarios")
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Consulta Exitosa")
                            .object(iUsuarioService.findById(id))
                            .build()
                    , HttpStatus.OK
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error en consulta")
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SignUpRequest signUpRequest) {
        try {
            iUsuarioService.update(id, signUpRequest);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Usuario actualizado exitosamente")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getReason())
                            .object(null)
                            .build()
                    , e.getStatusCode()
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al actualizar usuario")
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            iUsuarioService.delete(id);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Usuario eliminado exitosamente")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getReason())
                            .object(null)
                            .build()
                    , e.getStatusCode()
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al eliminar usuario")
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }


}
