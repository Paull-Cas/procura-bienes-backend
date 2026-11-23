package com.microservice.requerimientos.bienes.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JustificacionDTO {

    private Long idJustificacion;

    private Long idRequerimiento;

    private String descripcion;

    private String estado;
}
