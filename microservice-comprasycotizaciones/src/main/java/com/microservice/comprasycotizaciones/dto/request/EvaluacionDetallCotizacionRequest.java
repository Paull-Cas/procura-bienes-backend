package com.microservice.comprasycotizaciones.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluacionDetallCotizacionRequest {
    private Long idDetalleCotizacion;
    private String estado;
}
