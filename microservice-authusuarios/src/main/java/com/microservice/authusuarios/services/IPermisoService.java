package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.response.PermisoResponse;

import java.util.List;

public interface IPermisoService {
    List<PermisoResponse> getAllPermisos();
    PermisoResponse getById(Long id);
    List<PermisoResponse> getPermisosByRol(Long idRol);
}
