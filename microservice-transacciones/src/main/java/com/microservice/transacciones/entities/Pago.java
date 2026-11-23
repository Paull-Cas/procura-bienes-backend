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
@Table(name = "pago")
public class Pago {

    @Id
    @Column(name = "id_pago")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @Column(name = "titulo")
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_factura")
    private Factura factura;

    @Column(name = "monto_pago")
    private BigDecimal montoPago;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "fecha_carga")
    private LocalDate fechaCarga;

    @Column(name = "proximo_pago_estimado")
    private LocalDate proximoPagoEstimado;

    @Column(name = "comprobante_file")
    private byte[] comprobanteFile;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @PrePersist
    public void crear() {
        this.fechaCarga = LocalDate.now();
    }
}
