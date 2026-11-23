package com.microservice.transacciones.mapper;


import com.microservice.transacciones.dto.request.PagoRequest;
import com.microservice.transacciones.dto.response.PagoResponse;
import com.microservice.transacciones.entities.Factura;
import com.microservice.transacciones.entities.Pago;

import java.util.List;
import java.util.stream.Collectors;

public class PagoMapper {

    public static Pago toEntity(PagoRequest pagoRequest, Factura factura) {
        return Pago.builder()
                .titulo(pagoRequest.getTitulo())
                .factura(factura)
                .montoPago(pagoRequest.getMontoPago())
                .tipoPago(pagoRequest.getTipoPago())
                .fechaPago(pagoRequest.getFechaPago())
                .proximoPagoEstimado(pagoRequest.getProximoPagoEstimado())
                .comprobanteFile(pagoRequest.getComprobanteFile())
                .descripcion(pagoRequest.getDescripcion())
                .build();
    }

    public static PagoResponse toResponse(Pago pago) {
        return PagoResponse.builder()
                .idPago(pago.getIdPago())
                .titulo(pago.getTitulo())
                .idFactura(pago.getFactura().getIdFactura())
                .montoPago(pago.getMontoPago())
                .tipoPago(pago.getTipoPago())
                .fechaPago(pago.getFechaPago())
                .fechaCarga(pago.getFechaCarga())
                .proximoPagoEstimado(pago.getProximoPagoEstimado())
                .comprobanteFile(pago.getComprobanteFile())
                .descripcion(pago.getDescripcion())
                .build();
    }

    public static List<PagoResponse> toResponseList(List<Pago> pagos) {
        return pagos.stream()
                .map(PagoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
