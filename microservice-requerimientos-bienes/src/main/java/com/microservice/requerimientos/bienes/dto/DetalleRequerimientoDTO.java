package com.microservice.requerimientos.bienes.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleRequerimientoDTO {

    private Long idDetalleRequerimiento;

    private Long idRequerimiento;

    private Long idBien;

    private String marca;

    private String unidadMedida;

    private String caracteristicaTecnica;

    private int cantidad;

    private boolean aplicaFichaTecnica;

    private byte[] fichaTecnica;

    private boolean aplicaCertificadoCalidad;

    private byte[] certificadoCalidad;
}
