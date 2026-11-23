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
public class PagoRequest {

    private String titulo;

    private Long idFactura;

    private BigDecimal montoPago;

    private String tipoPago;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPago;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate proximoPagoEstimado;

    private byte[] comprobanteFile;

    private String descripcion;

}
