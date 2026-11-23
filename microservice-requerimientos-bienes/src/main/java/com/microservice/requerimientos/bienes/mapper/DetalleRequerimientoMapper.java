package com.microservice.requerimientos.bienes.mapper;

import com.microservice.requerimientos.bienes.dto.DetalleRequerimientoDTO;
import com.microservice.requerimientos.bienes.entities.Bien;
import com.microservice.requerimientos.bienes.entities.DetalleRequerimiento;
import com.microservice.requerimientos.bienes.entities.Requerimiento;

import java.util.List;
import java.util.stream.Collectors;

public class DetalleRequerimientoMapper {

    public static DetalleRequerimiento toEntity(DetalleRequerimientoDTO dto, Requerimiento req, Bien bien){
        return DetalleRequerimiento.builder()
                .idDetalleRequerimiento(dto.getIdDetalleRequerimiento())
                .requerimiento(req)
                .bien(bien)
                .marca(dto.getMarca())
                .unidadMedida(dto.getUnidadMedida())
                .caracteristicaTecnica(dto.getCaracteristicaTecnica())
                .cantidad(dto.getCantidad())
                .aplicaFichaTecnica(dto.isAplicaFichaTecnica())
                .fichaTecnica(dto.getFichaTecnica())
                .aplicaCertificadoCalidad(dto.isAplicaCertificadoCalidad())
                .certificadoCalidad(dto.getCertificadoCalidad())
                .build();
    }

    public static DetalleRequerimientoDTO toDTO(DetalleRequerimiento entity){
        return DetalleRequerimientoDTO.builder()
                .idDetalleRequerimiento(entity.getIdDetalleRequerimiento())
                .idRequerimiento(entity.getRequerimiento().getIdRequerimiento())
                .idBien(entity.getBien().getIdBien())
                .marca(entity.getMarca())
                .unidadMedida(entity.getUnidadMedida())
                .caracteristicaTecnica(entity.getCaracteristicaTecnica())
                .cantidad(entity.getCantidad())
                .aplicaFichaTecnica(entity.isAplicaFichaTecnica())
                .fichaTecnica(entity.getFichaTecnica())
                .aplicaCertificadoCalidad(entity.isAplicaCertificadoCalidad())
                .certificadoCalidad(entity.getCertificadoCalidad())
                .build();
    }

    public static List<DetalleRequerimiento> toEntityList(List<DetalleRequerimientoDTO> dtos, Requerimiento req, Bien bien){
        return dtos.stream()
                .map(dto -> toEntity(dto, req, bien))
                .collect(Collectors.toList());
    }

    public static List<DetalleRequerimientoDTO> toDTOList(List<DetalleRequerimiento> entities){
        return entities.stream()
                .map(DetalleRequerimientoMapper::toDTO)
                .collect(Collectors.toList());
    }

}
