package com.microservice.authusuarios.mapper;

import com.microservice.authusuarios.dto.response.ModuloResponse;
import com.microservice.authusuarios.entities.Modulo;

import java.util.List;
import java.util.stream.Collectors;

public class ModuloMapper {

    public static ModuloResponse toDTO(Modulo modulo) {
        Long dependenciaId = null;
        if (modulo.getDependencia() != null) {
            dependenciaId = modulo.getDependencia().getIdModulo();
        }
        return ModuloResponse.builder()
                .idModulo(modulo.getIdModulo())
                .nombre(modulo.getNombre())
                .descripcion(modulo.getDescripcion())
                .ruta(modulo.getRuta())
                .icono(modulo.getIcono())
                .dependencia(dependenciaId)
                .build();
    }

    public static List<ModuloResponse> toDTOList(List<Modulo> modulos) {
        return modulos.stream()
                .map(ModuloMapper::toDTO)
                .collect(Collectors.toList());
    }
}
