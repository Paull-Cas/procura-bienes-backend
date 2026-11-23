package com.microservice.requerimientos.bienes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BienDTO {

    private Long idBien;

    private String nombre;

    private String descripcion;

    private String categoria;
}
