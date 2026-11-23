package com.microservice.authusuarios.dto.response;

import com.microservice.authusuarios.entities.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosReponse {

    private Long idUsuario;

    private String nombreUsuario;

    private String alias;

    private String email;

    private int estado;

    private LocalDateTime ultimoLog;

    private Long idRol;
}
