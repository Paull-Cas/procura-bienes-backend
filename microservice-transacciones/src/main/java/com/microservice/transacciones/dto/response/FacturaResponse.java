package com.microservice.transacciones.dto.response;

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
public class FacturaResponse {

    private Long idFactura;

    private String titulo;

    private Long idUsuario;

    private String nombreUsuario;

    private Long idOrdenOperativa;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEmision;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCarga;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLimite;

    private BigDecimal montoTotal;

    private BigDecimal montoPagado;

    private String moneda;

    private String estadoFactura;

    private byte[] facturaFile;

    private String descripcion;

    private String observacion;

    private BigDecimal porcentajeDetraccion;
}
