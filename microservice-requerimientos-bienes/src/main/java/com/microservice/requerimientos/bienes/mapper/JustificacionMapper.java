package com.microservice.requerimientos.bienes.mapper;

import com.microservice.requerimientos.bienes.dto.JustificacionDTO;
import com.microservice.requerimientos.bienes.entities.Justificacion;
import com.microservice.requerimientos.bienes.entities.Requerimiento;

import java.util.List;
import java.util.stream.Collectors;

public class JustificacionMapper {

    public static Justificacion toEntity(JustificacionDTO dto, Requerimiento req) {
        return Justificacion.builder()
                .requerimiento(req)
                .descripcion(dto.getDescripcion())
                .estado(dto.getEstado())
                .build();
    }

    public static JustificacionDTO toDTO(Justificacion entity) {
        return JustificacionDTO.builder()
                .idJustificacion(entity.getIdJustificacion())
                .idRequerimiento(entity.getRequerimiento().getIdRequerimiento())
                .descripcion(entity.getDescripcion())
                .estado(entity.getEstado())
                .build();
    }

    public static List<Justificacion> toEntityList(List<JustificacionDTO> dtos, Requerimiento req) {
        return dtos.stream()
                .map(dto -> toEntity(dto, req))
                .collect(Collectors.toList());
    }

    public static List<JustificacionDTO> toDTOList(List<Justificacion> entities) {
        return entities.stream()
                .map(JustificacionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
