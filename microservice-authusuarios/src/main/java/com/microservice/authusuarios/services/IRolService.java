package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.request.RolRequest;
import com.microservice.authusuarios.dto.response.RolResponse;

import java.util.List;

public interface IRolService {
    void saveRol(RolRequest rol, List<Long> idPermisos);

    List<RolResponse> getAllRols();

    void updateRol(Long rolId, RolRequest rol, List<Long> idPermisos);

    void deleteRol(Long rolId);
}
