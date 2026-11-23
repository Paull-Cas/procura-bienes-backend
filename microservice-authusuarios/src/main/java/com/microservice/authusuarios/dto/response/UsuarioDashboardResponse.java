package com.microservice.authusuarios.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDashboardResponse {
    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private String rol;
    private int estado;
    private List<PermisosResponse> permisos;
}

