package com.microservice.comprasycotizaciones.mapper;

import com.microservice.comprasycotizaciones.dto.request.CompraRequest;
import com.microservice.comprasycotizaciones.dto.response.CompraResponse;
import com.microservice.comprasycotizaciones.entities.Compra;
import com.microservice.comprasycotizaciones.entities.Cotizacion;

import java.util.List;
import java.util.stream.Collectors;

public class CompraMapper {

    public static Compra toEntity(CompraRequest compraRequest, Cotizacion cotizacion) {
        return Compra.builder()
                .cotizacion(cotizacion)
                .fechaCompra(compraRequest.getFechaCompra())
                .gestorCompra(compraRequest.getGestorCompra())
                .nombreEmpresa(compraRequest.getNombreEmpresa())
                .direccion(compraRequest.getDireccion())
                .ruc(compraRequest.getRuc())
                .email(compraRequest.getEmail())
                .formaPago(compraRequest.getFormaPago())
                .plazoMinEntrega(compraRequest.getPlazoMinEntrega())
                .plazoMaxEntrega(compraRequest.getPlazoMaxEntrega())
                .nota(compraRequest.getNota())
                .descuento(compraRequest.getDescuento())
                .precioSubtotal(compraRequest.getPrecioSubtotal())
                .firmaGerente(compraRequest.getFirmaGerente())
                .firmaAdministracion(compraRequest.getFirmaAdministracion())
                .documentoCompra(compraRequest.getDocumentoCompra())
                .estado(compraRequest.getEstado())
                .build();
    }

    public static CompraResponse toResponse(Compra compra) {
        return CompraResponse.builder()
                .idCompra(compra.getIdCompra())
                .idCotizacion(compra.getCotizacion().getIdCotizacion())
                .idUsuario(compra.getCotizacion().getIdUsuario())
                .codigoOrdenCompra(compra.getCodigoOrdenCompra())
                .fechaCompra(compra.getFechaCompra())
                .gestorCompra(compra.getGestorCompra())
                .nombreEmpresa(compra.getNombreEmpresa())
                .direccion(compra.getDireccion())
                .ruc(compra.getRuc())
                .email(compra.getEmail())
                .formaPago(compra.getFormaPago())
                .plazoMinEntrega(compra.getPlazoMinEntrega())
                .plazoMaxEntrega(compra.getPlazoMaxEntrega())
                .nota(compra.getNota())
                .descuento(compra.getDescuento())
                .precioSubtotal(compra.getPrecioSubtotal())
                .firmaGerente(compra.getFirmaGerente())
                .firmaAdministracion(compra.getFirmaAdministracion())
                .documentoCompra(compra.getDocumentoCompra())
                .estado(compra.getEstado())
                .build();
    }

    public static List<CompraResponse> toListResponse(List<Compra> compras) {
        return compras.stream().map(CompraMapper::toResponse).collect(Collectors.toList());
    }
}
