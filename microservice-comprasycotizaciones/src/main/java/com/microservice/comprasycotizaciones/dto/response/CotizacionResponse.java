package com.microservice.comprasycotizaciones.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionResponse {

    private Long idCotizacion;

    private Long idProcura;

    private Long idUsuario;

    private String codigoCotizacion;

    private String numeroCotizacion;

    private String servicioOfertado;

    private String moneda;

    private String garantia;

    private String formaPago;

    private LocalDate plazoMinEntrega;

    private LocalDate plazoMaxEntrega;

    private String notas;

    private String informacionPago;

    private byte[] documentoCotizacion;

    private LocalDate fechaCotizacion;

    private LocalDate fechaLimiteCompra;

    private String estado;

    private BigDecimal precioSubtotal;

    private String personaContacto;

    private String telefonoMovil;

    private String telefonoOficina;

    private String email;

    private boolean incluyeIgv;

    private boolean incluyeCostosTransporte;

    private BigDecimal costosTransporte;
}
