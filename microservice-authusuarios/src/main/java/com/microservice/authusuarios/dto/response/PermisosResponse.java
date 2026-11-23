package com.microservice.authusuarios.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermisosResponse {
    private String nombre;
    private String codigoPermiso;
    private String descripcion;
}
