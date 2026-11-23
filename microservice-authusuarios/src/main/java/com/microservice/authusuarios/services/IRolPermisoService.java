package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.response.RolPermisoResponse;
import com.microservice.authusuarios.entities.Rol;

import java.util.List;

public interface IRolPermisoService {
    void saveRolPermiso(Rol rol, List<Long> idPermisos);

    List<RolPermisoResponse> getPermisosPorRol();

    void updateRolPermiso(Rol rol, List<Long> idPermisos);
}
