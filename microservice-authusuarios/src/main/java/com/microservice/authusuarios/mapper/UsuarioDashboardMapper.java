package com.microservice.authusuarios.mapper;

import com.microservice.authusuarios.dto.response.PermisosResponse;
import com.microservice.authusuarios.dto.response.UsuarioDashboardResponse;
import com.microservice.authusuarios.entities.Usuario;

import java.util.List;


public class UsuarioDashboardMapper {

    public static UsuarioDashboardResponse toDTO(Usuario usuario, List<PermisosResponse> permisos) {
        return UsuarioDashboardResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombreUsuario(usuario.getNombreUsuario())
                .email(usuario.getEmail())
                .rol(usuario.getRol().getNombre())
                .permisos(permisos)
                .build();
    }
}
