package com.microservice.comprasycotizaciones.dto.response;

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
public class CompraResponse {


    private Long idCompra;

    private Long idCotizacion;

    private Long idUsuario;

    private String codigoOrdenCompra;

    private LocalDate fechaCompra;

    private String gestorCompra;

    private String nombreEmpresa;

    private String direccion;

    private String ruc;

    private String email;

    private String formaPago;

    private LocalDate plazoMinEntrega;

    private LocalDate plazoMaxEntrega;

    private String nota;

    private BigDecimal descuento;

    private BigDecimal precioSubtotal;

    private byte [] firmaGerente;

    private byte [] firmaAdministracion;

    private byte [] documentoCompra;

    private String estado;

}
