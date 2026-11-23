package com.microservice.authusuarios.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolRequest {
    private String nombre;

    private String descripcion;

    private int estado;
}
