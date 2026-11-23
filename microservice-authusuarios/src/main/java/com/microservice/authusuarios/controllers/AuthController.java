package com.microservice.authusuarios.controllers;

import com.microservice.authusuarios.entities.Rol;
import com.microservice.authusuarios.entities.Usuario;
import com.microservice.authusuarios.dto.request.LoginRequest;
import com.microservice.authusuarios.dto.request.SignUpRequest;
import com.microservice.authusuarios.repository.IRolRepository;
import com.microservice.authusuarios.repository.IUsuarioRepository;
import com.microservice.authusuarios.security.jwt.JwtUtils;
import com.microservice.authusuarios.services.IUsuarioService;
import com.microservice.authusuarios.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(jwt);
    }

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<String> handleResponseStatusException(ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        usuarioService.save(signUpRequest);
        return ResponseEntity.ok("Usuario registrado exitosamente.");
    }
}
