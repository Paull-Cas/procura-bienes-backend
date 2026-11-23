package com.microservice.authusuarios.mapper;

import com.microservice.authusuarios.dto.response.UsuarioResponse;
import com.microservice.authusuarios.dto.response.UsuariosReponse;
import com.microservice.authusuarios.entities.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioResponse toDTO(Usuario usuario) {
        return UsuarioResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombreUsuario(usuario.getNombreUsuario())
                .build();
    }

    public static List<UsuarioResponse> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper:: toDTO)
                .collect(Collectors.toList());
    }

    public static UsuariosReponse toResponse(Usuario usuario) {
        return UsuariosReponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombreUsuario(usuario.getNombreUsuario())
                .alias(usuario.getAlias())
                .email(usuario.getEmail())
                .estado(usuario.getEstado())
                .ultimoLog(usuario.getUltimoLog())
                .idRol(usuario.getRol() != null ? usuario.getRol().getIdRol() : null)
                .build();
    }

    public static List<UsuariosReponse> toResponseList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}
