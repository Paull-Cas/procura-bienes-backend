package com.microservice.authusuarios.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolPermisoRequest {
    private RolRequest rolRequest;

    private List<Long> permisosRequest;
}
