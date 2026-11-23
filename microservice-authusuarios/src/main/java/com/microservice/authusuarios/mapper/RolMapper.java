package com.microservice.authusuarios.mapper;

import com.microservice.authusuarios.dto.request.RolRequest;
import com.microservice.authusuarios.dto.response.RolResponse;
import com.microservice.authusuarios.entities.Rol;

import java.util.List;
import java.util.stream.Collectors;

public class RolMapper {

    public static RolResponse toResponse(Rol rol) {
        return RolResponse.builder()
                .idRol(rol.getIdRol())
                .nombre(rol.getNombre())
                .descripcion(rol.getDescripcion())
                .estado(rol.getEstado())
                .build();
    }

    public static Rol toEntity(RolRequest rolRequest) {
        return Rol.builder()
                .nombre(rolRequest.getNombre())
                .descripcion(rolRequest.getDescripcion())
                .estado(rolRequest.getEstado())
                .build();
    }

    public static List<RolResponse> toResponseList(List<Rol> roles) {
        return roles.stream()
                .map(RolMapper :: toResponse)
                .collect(Collectors.toList());
    }
}
