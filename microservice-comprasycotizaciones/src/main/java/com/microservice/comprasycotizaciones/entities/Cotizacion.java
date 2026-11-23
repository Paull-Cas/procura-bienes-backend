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
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @Column(name = "id_cotizacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCotizacion;

    @ManyToOne
    @JoinColumn(name = "id_procura")
    private Procura procura;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "codigo_cotizacion")
    private String codigoCotizacion;

    @Column(name = "numero_cotizacion")
    private String numeroCotizacion;

    @Column(name = "servicio_ofertado", columnDefinition = "TEXT")
    private String servicioOfertado;

    @Column(name = "moneda")
    private String moneda;

    @Column(name = "garantia", columnDefinition = "TEXT")
    private String garantia;

    @Column(name = "forma_pago", columnDefinition = "TEXT")
    private String formaPago;

    @Column(name = "plazo_min_entrega")
    private LocalDate plazoMinEntrega;

    @Column(name = "plazo_max_entrega")
    private LocalDate plazoMaxEntrega;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    @Column(name = "informacion_pago", columnDefinition = "TEXT")
    private String informacionPago;

    @Column(name = "documento_cotizacion")
    private byte[] documentoCotizacion;

    @Column(name = "fecha_cotizacion")
    private LocalDate fechaCotizacion;

    @Column(name = "fecha_limite_compra")
    private LocalDate fechaLimiteCompra;

    @Column(name = "estado")
    private String estado;

    @Column(name = "precio_subtotal")
    private BigDecimal precioSubtotal;

    @Column(name = "persona_contacto")
    private String personaContacto;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @Column(name = "telefono_oficina")
    private String telefonoOficina;

    @Column(name = "email")
    private String email;

    @Column(name = "incluye_igv")
    private boolean incluyeIgv;

    @Column(name = "incluye_costos_transporte")
    private boolean incluyeCostosTransporte;

    @Column(name = "costos_transporte")
    private BigDecimal costosTransporte;
}
