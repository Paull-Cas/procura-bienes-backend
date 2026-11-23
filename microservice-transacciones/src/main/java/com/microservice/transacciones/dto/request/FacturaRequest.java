package com.microservice.transacciones.dto.request;

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
public class FacturaRequest {

    private String titulo;

    private Long idUsuario;

    private Long idOrdenOperativa;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEmision;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLimite;

    private BigDecimal montoTotal;

    private String moneda;

    private String estadoFactura;

    private byte[] facturaFile;

    private String descripcion;

    private String observacion;

    private BigDecimal porcentajeDetraccion;
}
