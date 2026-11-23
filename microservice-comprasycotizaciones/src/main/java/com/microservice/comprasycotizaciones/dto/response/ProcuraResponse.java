package com.microservice.comprasycotizaciones.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcuraResponse {

    private Long idProcura;

    private String codigoProcura;

    private Long idUsuario;

    private String tipoRequerimiento;

    private String estado;

    private LocalDate fechaMaximaCotizacion;

    private LocalDate fechaMaximaAbastecimiento;
}
