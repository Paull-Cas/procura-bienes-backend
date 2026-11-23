package com.microservice.authusuarios.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuloResponse {

    private Long idModulo;

    private String nombre;

    private String descripcion;

    private String ruta;

    private String icono;

    private Long dependencia;
}
