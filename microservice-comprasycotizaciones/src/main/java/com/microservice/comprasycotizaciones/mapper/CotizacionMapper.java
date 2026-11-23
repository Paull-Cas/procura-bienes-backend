package com.microservice.comprasycotizaciones.mapper;

import com.microservice.comprasycotizaciones.dto.request.CotizacionRequest;
import com.microservice.comprasycotizaciones.dto.response.CotizacionResponse;
import com.microservice.comprasycotizaciones.entities.Cotizacion;
import com.microservice.comprasycotizaciones.entities.Procura;

import java.util.List;
import java.util.stream.Collectors;

public class CotizacionMapper {

    public static Cotizacion toEntity(CotizacionRequest cotizacionRequest, Procura procura) {
        return Cotizacion.builder()
                .procura(procura)
                .idUsuario(cotizacionRequest.getIdUsuario())
                .numeroCotizacion(cotizacionRequest.getNumeroCotizacion())
                .servicioOfertado(cotizacionRequest.getServicioOfertado())
                .moneda(cotizacionRequest.getMoneda())
                .garantia(cotizacionRequest.getGarantia())
                .formaPago(cotizacionRequest.getFormaPago())
                .plazoMaxEntrega(cotizacionRequest.getPlazoMaxEntrega())
                .plazoMinEntrega(cotizacionRequest.getPlazoMinEntrega())
                .notas(cotizacionRequest.getNotas())
                .informacionPago(cotizacionRequest.getInformacionPago())
                .documentoCotizacion(cotizacionRequest.getDocumentoCotizacion())
                .fechaCotizacion(cotizacionRequest.getFechaCotizacion())
                .fechaLimiteCompra(cotizacionRequest.getFechaLimiteCompra())
                .estado(cotizacionRequest.getEstado())
                .precioSubtotal(cotizacionRequest.getPrecioSubtotal())
                .personaContacto(cotizacionRequest.getPersonaContacto())
                .telefonoMovil(cotizacionRequest.getTelefonoMovil())
                .telefonoOficina(cotizacionRequest.getTelefonoOficina())
                .email(cotizacionRequest.getEmail())
                .incluyeIgv(cotizacionRequest.isIncluyeIgv())
                .incluyeCostosTransporte(cotizacionRequest.isIncluyeCostosTransporte())
                .costosTransporte(cotizacionRequest.getCostosTransporte())
                .build();
    }

    public static CotizacionResponse toResponse(Cotizacion cotizacion) {
        return CotizacionResponse.builder()
                .idCotizacion(cotizacion.getIdCotizacion())
                .idProcura(cotizacion.getProcura().getIdProcura())
                .idUsuario(cotizacion.getIdUsuario())
                .codigoCotizacion(cotizacion.getCodigoCotizacion())
                .numeroCotizacion(cotizacion.getNumeroCotizacion())
                .servicioOfertado(cotizacion.getServicioOfertado())
                .moneda(cotizacion.getMoneda())
                .garantia(cotizacion.getGarantia())
                .formaPago(cotizacion.getFormaPago())
                .plazoMaxEntrega(cotizacion.getPlazoMaxEntrega())
                .plazoMinEntrega(cotizacion.getPlazoMinEntrega())
                .notas(cotizacion.getNotas())
                .informacionPago(cotizacion.getInformacionPago())
                .documentoCotizacion(cotizacion.getDocumentoCotizacion())
                .fechaCotizacion(cotizacion.getFechaCotizacion())
                .fechaLimiteCompra(cotizacion.getFechaLimiteCompra())
                .estado(cotizacion.getEstado())
                .precioSubtotal(cotizacion.getPrecioSubtotal())
                .personaContacto(cotizacion.getPersonaContacto())
                .telefonoMovil(cotizacion.getTelefonoMovil())
                .telefonoOficina(cotizacion.getTelefonoOficina())
                .email(cotizacion.getEmail())
                .incluyeIgv(cotizacion.isIncluyeIgv())
                .incluyeCostosTransporte(cotizacion.isIncluyeCostosTransporte())
                .costosTransporte(cotizacion.getCostosTransporte())
                .build();
    }

    public static List<CotizacionResponse> toListResponse(List<Cotizacion> cotizaciones) {
        return cotizaciones.stream().map(CotizacionMapper::toResponse).collect(Collectors.toList());
    }
}
