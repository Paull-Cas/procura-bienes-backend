package com.microservice.transacciones.entities;

import jakarta.persistence.*;
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
@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @Column(name = "id_factura")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_orden_operativa")
    private Long idOrdenOperativa;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "fecha_carga", updatable = false)
    private LocalDate fechaCarga;

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;

    @Column(name = "monto_total")
    private BigDecimal montoTotal;

    @Column(name = "monto_pagado")
    private BigDecimal montoPagado;

    @Column(name = "moneda")
    private String moneda;

    @Column(name = "estado_factura")
    private String estadoFactura;

    @Column(name = "factura_file")
    private byte[] facturaFile;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "porcentaje_detraccion")
    private BigDecimal porcentajeDetraccion;

    @PrePersist
    public void crear() {
        this.fechaCarga = LocalDate.now();
        this.estadoFactura = "Pendiente";
        this.montoPagado = BigDecimal.ZERO;
    }
}
