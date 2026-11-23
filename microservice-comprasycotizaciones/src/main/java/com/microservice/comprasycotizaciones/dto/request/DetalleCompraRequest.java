package com.microservice.comprasycotizaciones.dto.request;

import com.microservice.comprasycotizaciones.entities.DetalleCotizacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCompraRequest {

    private Long idDetalleCompra;

    private Long idDetalleCotizacion;

    private Long idCompra;

    private int cantidadComprada;

    private BigDecimal precioCompra;

    private String observaciones;

}
