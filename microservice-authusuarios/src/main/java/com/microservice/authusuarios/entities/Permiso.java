package com.microservice.authusuarios.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permiso", uniqueConstraints = @UniqueConstraint(columnNames = "codigo_permiso"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long idPermiso;

    @Column(name = "codigo_permiso")
    private String codigoPermiso;

    private String nombre;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;

    @OneToMany(mappedBy = "permiso", fetch = FetchType.LAZY)
    private List<RolPermiso> rolPermisos = new ArrayList<>();
}