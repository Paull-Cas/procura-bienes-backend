package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.dto.request.DetalleCotizacionRequest;
import com.microservice.comprasycotizaciones.dto.request.EvaluacionDetallCotizacionRequest;
import com.microservice.comprasycotizaciones.dto.response.DetalleCotizacionResponse;

import java.util.List;

public interface IDetalleCotizacionService {
    List<DetalleCotizacionResponse> getAllByCotizacion(Long idCotizacion);
    DetalleCotizacionResponse getById(Long idCotizacion);
    void update(Long idCotizacion, List<DetalleCotizacionRequest> listaItems);
    void deleteById(Long id);
    void evaluarDetalleCotizacion(Long idCotizacion, List<EvaluacionDetallCotizacionRequest> requests);
}
