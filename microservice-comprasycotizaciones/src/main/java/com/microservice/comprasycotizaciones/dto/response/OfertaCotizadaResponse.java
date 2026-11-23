package com.microservice.comprasycotizaciones.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfertaCotizadaResponse {

    private Long idDetalleCotizacion;

    private Long idCotizacion;

    private String codigoCotizacion;

    private Long idUsuario;

    private String proveedor;

    private Long idDetalleRequerimiento;

    private int cantidad;

    private BigDecimal precioUnitario;

    private String estado;

    private String moneda;
}
