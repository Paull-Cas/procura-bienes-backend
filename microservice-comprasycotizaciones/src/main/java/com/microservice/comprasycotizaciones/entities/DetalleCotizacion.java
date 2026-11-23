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
@Table(name = "detalle_cotizacion")
public class DetalleCotizacion {

    @Id
    @Column(name = "id_detalle_cotizacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCotizacion;

    @ManyToOne
    @JoinColumn(name = "id_cotizacion")
    private Cotizacion cotizacion;

    @Column(name = "id_detalle_requerimiento")
    private Long idDetalleRequerimiento;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "estado")
    private String estado;

    @Column(name = "ficha_tecnica")
    private String fichaTecnica;

    @Column(name = "brinda_certificado_calidad")
    private boolean brindaCertificadoCalidad;

    @PrePersist
    protected void onCreate() {
        estado = "Pendiente";
    }
}
