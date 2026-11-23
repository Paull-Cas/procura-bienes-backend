package com.microservice.requerimientos.bienes.mapper;

import com.microservice.requerimientos.bienes.dto.response.TargetResponse;
import com.microservice.requerimientos.bienes.client.Response.UsuarioResponse;
import com.microservice.requerimientos.bienes.entities.Requerimiento;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TargetMapper {
    public static Optional<TargetResponse> toTarget(Requerimiento requerimiento, List<UsuarioResponse> usuarios) {
        return usuarios.stream()
                .filter(u -> u.getIdUsuario().equals(requerimiento.getIdUsuario()))
                .findFirst()
                .map(usuario -> TargetResponse.builder()
                        .idRequerimiento(requerimiento.getIdRequerimiento())
                        .idUsuario(usuario.getIdUsuario())
                        .solicitante(usuario.getNombreUsuario()) // Asume que existe este método
                        .titulo(requerimiento.getTitulo())
                        .obra(requerimiento.getObra())
                        .estado(requerimiento.getEstado())
                        .fechaRequerimiento(requerimiento.getFechaRequerimiento())
                        .fechaMaximaAbastecimiento(requerimiento.getFechaMaximaAbastecimiento())
                        .build());
    }

    public static List<TargetResponse> toListTarget(List<Requerimiento> requerimientos, List<UsuarioResponse> usuarios) {
        return requerimientos.stream()
                .map(requerimiento -> toTarget(requerimiento, usuarios))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
