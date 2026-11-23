package com.microservice.comprasycotizaciones.dto.request;

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
public class ProcuraRequest {

    private Long idProcura;

    private String codigoProcura;

    private Long idUsuario;

    private String tipoRequerimiento;

    private String estado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaMaximaCotizacion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaMaximaAbastecimiento;
}
