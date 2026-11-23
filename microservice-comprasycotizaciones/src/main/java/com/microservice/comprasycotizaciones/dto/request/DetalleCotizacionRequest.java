package com.microservice.comprasycotizaciones.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCotizacionRequest {

    private Long idDetalleCotizacion;

    private Long idCotizacion;

    private Long idDetalleRequerimiento;

    private int cantidad;

    private BigDecimal precioUnitario;

    private String estado;

    private String fichaTecnica;

    private boolean brindaCertificadoCalidad;
}
