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
@Table(name = "justificacion")
public class Justificacion {

    @Id
    @Column(name = "id_justificacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJustificacion;

    @ManyToOne
    @JoinColumn(name = "id_requerimiento")
    private Requerimiento requerimiento;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "estado")
    private String estado;
}
