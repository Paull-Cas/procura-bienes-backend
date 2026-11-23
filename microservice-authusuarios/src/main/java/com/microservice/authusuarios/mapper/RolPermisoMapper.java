package com.microservice.authusuarios.mapper;

import com.microservice.authusuarios.dto.response.PermisoResponse;
import com.microservice.authusuarios.dto.response.RolPermisoResponse;
import com.microservice.authusuarios.dto.response.RolResponse;
import com.microservice.authusuarios.entities.Permiso;
import com.microservice.authusuarios.entities.Rol;
import com.microservice.authusuarios.entities.RolPermiso;

import java.util.List;

public class RolPermisoMapper {

    public static RolPermiso toEntity(Rol rol, Permiso permiso) {
        return RolPermiso.builder()
                .rol(rol)
                .permiso(permiso)
                .build();
    }

    public static RolPermisoResponse toResponse(RolResponse rol, List<PermisoResponse> permisos) {
        return RolPermisoResponse.builder()
                .rolResponse(rol)
                .permisosResponse(permisos)
                .build();
    }
}
