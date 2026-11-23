package com.microservice.comprasycotizaciones.entities;

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
@Table(name = "procura")
public class Procura {

    @Id
    @Column(name = "id_procura")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcura;

    @Column(name = "codigo_procura")
    private String codigoProcura;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "tipo_requerimiento")
    private String tipoRequerimiento;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_maxima_cotizacion")
    private LocalDate fechaMaximaCotizacion;

    @Column(name = "fecha_maxima_abastecimiento")
    private LocalDate fechaMaximaAbastecimiento;

}
