package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.response.ModuloResponse;

import java.util.List;

public interface IModuloService {

    List<ModuloResponse> obtenerModulosPorPermisos(List<String> permisos);

    List<ModuloResponse> getAll();
}
