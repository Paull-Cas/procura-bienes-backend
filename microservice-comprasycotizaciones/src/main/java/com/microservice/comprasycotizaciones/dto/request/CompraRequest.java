package com.microservice.comprasycotizaciones.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microservice.comprasycotizaciones.entities.Cotizacion;
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
public class CompraRequest {

    private Long idCompra;

    private Long idCotizacion;

    private LocalDate fechaCompra;

    private String gestorCompra;

    private String nombreEmpresa;

    private String direccion;

    private String ruc;

    private String email;

    private String formaPago;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate plazoMinEntrega;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate plazoMaxEntrega;

    private String nota;

    private BigDecimal descuento;

    private BigDecimal precioSubtotal;

    private byte [] firmaGerente;

    private byte [] firmaAdministracion;

    private byte [] documentoCompra;

    private String estado;
}
