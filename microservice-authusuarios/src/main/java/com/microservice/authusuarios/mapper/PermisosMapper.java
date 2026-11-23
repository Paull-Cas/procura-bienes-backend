package com.microservice.authusuarios.mapper;

import com.microservice.authusuarios.dto.response.PermisosResponse;
import com.microservice.authusuarios.entities.Permiso;

import java.util.List;
import java.util.stream.Collectors;

public class PermisosMapper {

    public static PermisosResponse toDTO(Permiso permiso) {
        return PermisosResponse.builder()
                .nombre(permiso.getNombre())
                .codigoPermiso(permiso.getCodigoPermiso())
                .descripcion(permiso.getDescripcion())
                .build();
    }

    public static List<PermisosResponse> toDTOList(List<Permiso> permisos) {
        return permisos.stream()
                .map(PermisosMapper :: toDTO)
                .collect(Collectors.toList());
    }
}
