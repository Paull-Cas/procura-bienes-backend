package com.microservice.comprasycotizaciones.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CotizacionRequest {

    private Long idCotizacion;

    private Long idProcura;

    private Long idUsuario;

    private String numeroCotizacion;

    private String servicioOfertado;

    private String moneda;

    private String garantia;

    private String formaPago;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate plazoMinEntrega;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate plazoMaxEntrega;

    private String notas;

    private String informacionPago;

    private byte[] documentoCotizacion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCotizacion;

    @JsonFormat(pattern = "yyyy-MM-dd")
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
