package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.dto.request.CotizacionConBienesRequest;
import com.microservice.comprasycotizaciones.dto.request.CotizacionRequest;
import com.microservice.comprasycotizaciones.dto.response.CotizacionResponse;

import java.util.List;

public interface ICotizacionService {
    String save(CotizacionConBienesRequest cotizacion);

    List<CotizacionResponse> getAll(Long idUsuario);

    CotizacionResponse getById(Long id);

    CotizacionResponse getByProcuraProveedor(Long idUsuario, Long idProcura);

    void update(CotizacionRequest cotizacion, Long id);

    void delete(Long id);
}
