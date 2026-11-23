package com.microservice.transacciones.mapper;

import com.microservice.transacciones.dto.request.FacturaRequest;
import com.microservice.transacciones.dto.response.FacturaResponse;
import com.microservice.transacciones.client.Response.UsuarioResponse;
import com.microservice.transacciones.entities.Factura;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FacturaMapper {

    public static Factura toEntity(FacturaRequest facturaRequest) {
        return Factura.builder()
                .titulo(facturaRequest.getTitulo())
                .idUsuario(facturaRequest.getIdUsuario())
                .idOrdenOperativa(facturaRequest.getIdOrdenOperativa())
                .fechaEmision(facturaRequest.getFechaEmision())
                .fechaLimite(facturaRequest.getFechaLimite())
                .montoTotal(facturaRequest.getMontoTotal())
                .moneda(facturaRequest.getMoneda())
                .estadoFactura(facturaRequest.getEstadoFactura())
                .facturaFile(facturaRequest.getFacturaFile())
                .descripcion(facturaRequest.getDescripcion())
                .observacion(facturaRequest.getObservacion())
                .porcentajeDetraccion(facturaRequest.getPorcentajeDetraccion())
                .build();
    }

    public static Optional<FacturaResponse> toResponse(Factura factura, List<UsuarioResponse> usuarios) {
        return usuarios.stream()
                .filter(u -> u.getIdUsuario().equals(factura.getIdUsuario()))
                .findFirst()
                .map(usuario -> FacturaResponse.builder()
                        .idFactura(factura.getIdFactura())
                        .titulo(factura.getTitulo())
                        .idUsuario(factura.getIdUsuario())
                        .nombreUsuario(usuario.getNombreUsuario())
                        .idOrdenOperativa(factura.getIdOrdenOperativa())
                        .fechaEmision(factura.getFechaEmision())
                        .fechaCarga(factura.getFechaCarga())
                        .fechaLimite(factura.getFechaLimite())
                        .montoTotal(factura.getMontoTotal())
                        .montoPagado(factura.getMontoPagado())
                        .moneda(factura.getMoneda())
                        .estadoFactura(factura.getEstadoFactura())
                        .facturaFile(factura.getFacturaFile())
                        .descripcion(factura.getDescripcion())
                        .observacion(factura.getObservacion())
                        .porcentajeDetraccion(factura.getPorcentajeDetraccion())
                        .build());
    }

    public static List<FacturaResponse> toResponseList(List<Factura> facturas, List<UsuarioResponse> usuarios) {
        return facturas.stream()
                .map(factura -> toResponse(factura, usuarios))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
