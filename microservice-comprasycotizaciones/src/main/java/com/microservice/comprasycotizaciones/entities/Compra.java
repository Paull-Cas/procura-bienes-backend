package com.microservice.comprasycotizaciones.entities;

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
@Table(name = "compra")
public class Compra {

    @Id
    @Column(name = "id_compra")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;

    @ManyToOne
    @JoinColumn(name = "id_cotizacion")
    private Cotizacion cotizacion;

    @Column(name = "codigo_orden_compra")
    private String codigoOrdenCompra;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(name = "gestor_compra")
    private String gestorCompra;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "email")
    private String email;

    @Column(name = "forma_pago")
    private String formaPago;

    @Column(name = "plazo_min_entrega")
    private LocalDate plazoMinEntrega;

    @Column(name = "plazo_max_entrega")
    private LocalDate plazoMaxEntrega;

    @Column(name = "nota")
    private String nota;

    @Column(name = "descuento")
    private BigDecimal descuento;

    @Column(name = "precio_subtotal")
    private BigDecimal precioSubtotal;

    @Column(name = "firma_gerente")
    private byte [] firmaGerente;

    @Column(name = "firma_administracion")
    private byte [] firmaAdministracion;

    @Column(name = "documento_compra")
    private byte [] documentoCompra;

    @Column(name = "estado")
    private String estado;

}
