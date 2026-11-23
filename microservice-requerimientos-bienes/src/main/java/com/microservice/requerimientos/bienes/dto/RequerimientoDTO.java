package com.microservice.requerimientos.bienes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequerimientoDTO {
    private Long idRequerimiento;
    private Long idUsuario;
    private String titulo;
    private String obra;
    private String tipoRequerimiento;
    private String estado;
    private String descripcion;
    private String observaciones;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRequerimiento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaMaximaAbastecimiento;
}
