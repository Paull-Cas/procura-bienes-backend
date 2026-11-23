package com.microservice.requerimientos.bienes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_requerimiento")
public class DetalleRequerimiento {

    @Id
    @Column(name = "id_detalle_requerimiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleRequerimiento;

    @ManyToOne
    @JoinColumn(name = "id_requerimiento")
    private Requerimiento requerimiento;

    @ManyToOne
    @JoinColumn(name = "id_bien")
    private Bien bien;

    @Column(name = "marca")
    private String marca;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "caracteristica_tecnica", columnDefinition = "TEXT")
    private String caracteristicaTecnica;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "aplica_ficha_tecnica")
    private boolean aplicaFichaTecnica;

    @Column(name = "ficha_tecnica")
    private byte[] fichaTecnica;

    @Column(name = "aplica_certificado_calidad")
    private boolean aplicaCertificadoCalidad;

    @Column(name = "certificado_calidad")
    private byte[] certificadoCalidad;
}
