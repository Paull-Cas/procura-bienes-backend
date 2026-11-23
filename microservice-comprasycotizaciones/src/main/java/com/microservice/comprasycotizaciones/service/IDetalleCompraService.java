package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.dto.request.DetalleCompraRequest;
import com.microservice.comprasycotizaciones.dto.response.DetalleCompraResponse;

import java.util.List;

public interface IDetalleCompraService {
    List<DetalleCompraResponse> getAllByCompra(Long idCompra);
    DetalleCompraResponse getById(Long idCompra);
    void update(Long idCompra,List<DetalleCompraRequest> listaItems);
    void deleteById(Long id);
}
