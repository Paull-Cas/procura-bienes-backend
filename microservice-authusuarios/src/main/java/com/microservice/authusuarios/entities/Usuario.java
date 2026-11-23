package com.microservice.authusuarios.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nombre_usuario"),
                @UniqueConstraint(columnNames = "email")
        })
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "alias")
    private String alias;

    @Column(name = "email")
    private String email;

    @Column(name = "estado")
    private int estado;

    @Column(name = "ultimo_log")
    private LocalDateTime ultimoLog;

    @Column(name = "contrasena")
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}