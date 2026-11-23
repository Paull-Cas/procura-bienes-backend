package com.microservice.comprasycotizaciones.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_compra")
public class DetalleCompra {

    @Id
    @Column(name = "id_detalle_compra")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCompra;

    @ManyToOne
    @JoinColumn(name = "id_detalle_cotizacion")
    private DetalleCotizacion detalleCotizacion;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @Column(name = "cantidad_comprada")
    private int cantidadComprada;

    @Column(name = "precio_compra")
    private BigDecimal precioCompra;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

}


