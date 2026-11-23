package com.microservice.requerimientos.bienes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requerimiento")
public class Requerimiento {

    @Id
    @Column(name = "id_requerimiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRequerimiento;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "obra")
    private String obra;

    @Column(name = "tipo_requerimiento")
    private String tipoRequerimiento;

    @Column(name = "estado")
    private String estado;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "fecha_requerimiento")
    private LocalDate fechaRequerimiento;

    @Column(name = "fecha_maxima_abastecimiento")
    private LocalDate fechaMaximaAbastecimiento;

    @PrePersist
    public void crear() {
        this.fechaRequerimiento = LocalDate.now();
        this.estado = "Pendiente";
    }
}
