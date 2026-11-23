package com.microservice.comprasycotizaciones.mapper;

import com.microservice.comprasycotizaciones.dto.request.DetalleCotizacionRequest;
import com.microservice.comprasycotizaciones.dto.response.DetalleCotizacionResponse;
import com.microservice.comprasycotizaciones.entities.Cotizacion;
import com.microservice.comprasycotizaciones.entities.DetalleCotizacion;

import java.util.List;
import java.util.stream.Collectors;

public class DetalleCotizacionMapper {

    public static DetalleCotizacion toEntity(DetalleCotizacionRequest request, Cotizacion cotizacion) {
        return DetalleCotizacion.builder()
                .cotizacion(cotizacion)
                .idDetalleRequerimiento(request.getIdDetalleRequerimiento())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .estado(request.getEstado())
                .fichaTecnica(request.getFichaTecnica())
                .brindaCertificadoCalidad(request.isBrindaCertificadoCalidad())
                .build();
    }

    public static DetalleCotizacionResponse toResponse(DetalleCotizacion entity) {
        return DetalleCotizacionResponse.builder()
                .idDetalleCotizacion(entity.getIdDetalleCotizacion())
                .idCotizacion(entity.getCotizacion().getIdCotizacion())
                .idDetalleRequerimiento(entity.getIdDetalleRequerimiento())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .estado(entity.getEstado())
                .fichaTecnica(entity.getFichaTecnica())
                .brindaCertificadoCalidad(entity.isBrindaCertificadoCalidad())
                .build();
    }

    public static List<DetalleCotizacionResponse> toListResponse(List<DetalleCotizacion> entities) {
        return entities.stream().map(DetalleCotizacionMapper::toResponse).collect(Collectors.toList());
    }
}
