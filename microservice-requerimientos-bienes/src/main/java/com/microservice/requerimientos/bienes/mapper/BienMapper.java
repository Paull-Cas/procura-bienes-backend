package com.microservice.requerimientos.bienes.mapper;

import com.microservice.requerimientos.bienes.dto.BienDTO;
import com.microservice.requerimientos.bienes.entities.Bien;

import java.util.List;
import java.util.stream.Collectors;

public class BienMapper {

    public static Bien toEntity(BienDTO dto) {
        return Bien.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .categoria(dto.getCategoria())
                .build();
    }

    public static BienDTO toDTO(Bien entity) {
        return BienDTO.builder()
                .idBien(entity.getIdBien())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .categoria(entity.getCategoria())
                .build();
    }

    public static List<BienDTO> toDTOList(List<Bien> bienes) {
        return bienes.stream()
                .map(BienMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Bien> toEntityList(List<BienDTO> dtos) {
        return dtos.stream()
                .map(BienMapper::toEntity)
                .collect(Collectors.toList());
    }
}
