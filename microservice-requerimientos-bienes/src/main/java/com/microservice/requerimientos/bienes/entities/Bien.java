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
@Table(name = "bien")
public class Bien {

    @Id
    @Column(name = "id_bien")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBien;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;
}
