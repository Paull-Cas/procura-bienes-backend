package com.microservice.authusuarios.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "modulo", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo")
    private Long idModulo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "ruta")
    private String ruta;

    @Column(name = "icono")
    private String icono;

    @ManyToOne
    @JoinColumn(name = "dependencia", nullable = true)
    private Modulo dependencia;

    @OneToMany(mappedBy = "modulo", fetch = FetchType.EAGER)
    private List<Permiso> permisos;
}
