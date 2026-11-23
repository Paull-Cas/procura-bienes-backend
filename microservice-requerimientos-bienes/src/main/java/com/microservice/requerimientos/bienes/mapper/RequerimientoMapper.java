package com.microservice.requerimientos.bienes.mapper;

import com.microservice.requerimientos.bienes.dto.RequerimientoDTO;
import com.microservice.requerimientos.bienes.dto.request.RequerimientoRequest;
import com.microservice.requerimientos.bienes.entities.Requerimiento;

public class RequerimientoMapper {

    public static Requerimiento toEntity(RequerimientoRequest requerimientoDTO) {
        return Requerimiento.builder()
                .idUsuario(requerimientoDTO.getIdUsuario())
                .titulo(requerimientoDTO.getTitulo())
                .obra(requerimientoDTO.getObra())
                .tipoRequerimiento(requerimientoDTO.getTipoRequerimiento())
                .estado(requerimientoDTO.getEstado())
                .descripcion(requerimientoDTO.getDescripcion())
                .observaciones(requerimientoDTO.getObservaciones())
                .fechaMaximaAbastecimiento(requerimientoDTO.getFechaMaximaAbastecimiento())
                .build();
    }

    public static RequerimientoDTO toDTO(Requerimiento entity) {
        return RequerimientoDTO.builder()
                .idRequerimiento(entity.getIdRequerimiento())
                .idUsuario(entity.getIdUsuario())
                .titulo(entity.getTitulo())
                .obra(entity.getObra())
                .tipoRequerimiento(entity.getTipoRequerimiento())
                .estado(entity.getEstado())
                .descripcion(entity.getDescripcion())
                .observaciones(entity.getObservaciones())
                .fechaRequerimiento(entity.getFechaRequerimiento())
                .fechaMaximaAbastecimiento(entity.getFechaMaximaAbastecimiento())
                .build();
    }
}
