package com.microservice.authusuarios.mapper;

import com.microservice.authusuarios.dto.response.PermisoResponse;
import com.microservice.authusuarios.entities.Permiso;

import java.util.List;
import java.util.stream.Collectors;

public class PermisoMapper {
    public static PermisoResponse toResponse(Permiso permiso) {
        return PermisoResponse.builder()
                .idPermiso(permiso.getIdPermiso())
                .codigoPermiso(permiso.getCodigoPermiso())
                .nombre(permiso.getNombre())
                .descripcion(permiso.getDescripcion())
                .idModulo(permiso.getModulo().getIdModulo())
                .build();
    }

    public static List<PermisoResponse> toResponseList(List<Permiso> permisos) {
        return permisos.stream()
                .map(PermisoMapper :: toResponse)
                .collect(Collectors.toList());
    }
}
