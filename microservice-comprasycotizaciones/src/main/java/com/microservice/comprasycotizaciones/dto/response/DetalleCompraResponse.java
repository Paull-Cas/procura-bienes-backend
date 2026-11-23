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
public class DetalleCompraResponse {

    private Long idDetalleCompra;

    private Long idDetalleCotizacion;

    private Long idCompra;

    private int cantidadComprada;

    private BigDecimal precioCompra;

    private String observaciones;
}
