package com.microservice.requerimientos.bienes.dto.response;

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
public class TargetResponse {

    private Long idRequerimiento;

    private Long idUsuario;

    private String solicitante;

    private String titulo;

    private String obra;

    private String estado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRequerimiento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaMaximaAbastecimiento;
}
