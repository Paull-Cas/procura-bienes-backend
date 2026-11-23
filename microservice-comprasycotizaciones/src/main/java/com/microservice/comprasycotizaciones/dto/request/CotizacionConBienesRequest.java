package com.microservice.comprasycotizaciones.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionConBienesRequest {

    private CotizacionRequest cotizacion;

    private List<DetalleCotizacionRequest> bienescotizados;
}
