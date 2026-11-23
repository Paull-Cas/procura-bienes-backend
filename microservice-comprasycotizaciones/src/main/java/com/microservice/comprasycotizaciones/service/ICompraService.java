package com.microservice.comprasycotizaciones.service;


import com.microservice.comprasycotizaciones.dto.request.CompraConBienesRequest;
import com.microservice.comprasycotizaciones.dto.request.CompraRequest;
import com.microservice.comprasycotizaciones.dto.response.CompraResponse;

import java.util.List;

public interface ICompraService {
    String save(CompraConBienesRequest compraRequest);

    List<CompraResponse> getAll(Long idUsuario);

    List<CompraResponse> getByProveedor(Long idUsuario);

    CompraResponse getById(Long id);

    void aceptar(Long id);

    void update(CompraRequest compraRequest, Long id);

    void delete(Long id);
}
