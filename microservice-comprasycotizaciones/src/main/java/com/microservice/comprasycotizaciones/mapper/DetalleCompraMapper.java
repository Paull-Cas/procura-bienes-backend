package com.microservice.comprasycotizaciones.mapper;

import com.microservice.comprasycotizaciones.dto.request.DetalleCompraRequest;
import com.microservice.comprasycotizaciones.dto.response.DetalleCompraResponse;
import com.microservice.comprasycotizaciones.entities.Compra;
import com.microservice.comprasycotizaciones.entities.DetalleCompra;
import com.microservice.comprasycotizaciones.entities.DetalleCotizacion;

import java.util.List;
import java.util.stream.Collectors;

public class DetalleCompraMapper {

    public static DetalleCompra toEntity(DetalleCompraRequest request, Compra compra, DetalleCotizacion detalleCotizacion) {
        return DetalleCompra.builder()
                .detalleCotizacion(detalleCotizacion)
                .compra(compra)
                .cantidadComprada(request.getCantidadComprada())
                .precioCompra(request.getPrecioCompra())
                .observaciones(request.getObservaciones())
                .build();
    }

    public static DetalleCompraResponse toResponse(DetalleCompra detalleCompra) {
        return DetalleCompraResponse.builder()
                .idDetalleCompra(detalleCompra.getIdDetalleCompra())
                .idDetalleCotizacion(detalleCompra.getDetalleCotizacion().getIdDetalleCotizacion())
                .idCompra(detalleCompra.getCompra().getIdCompra())
                .cantidadComprada(detalleCompra.getCantidadComprada())
                .precioCompra(detalleCompra.getPrecioCompra())
                .observaciones(detalleCompra.getObservaciones())
                .build();
    }

    public static List<DetalleCompraResponse> toResponse(List<DetalleCompra> detalleCompras) {
        return detalleCompras.stream().map(DetalleCompraMapper::toResponse).collect(Collectors.toList());
    }
}