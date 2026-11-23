package com.microservice.authusuarios.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String nombreUsuario;

    @NotBlank
    @Size(min = 3, max = 50)
    private String alias;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    private int estado;

    private Long idRol;

    @NotBlank
    @Size(min = 6, max = 100)
    private String contrasena;
}
