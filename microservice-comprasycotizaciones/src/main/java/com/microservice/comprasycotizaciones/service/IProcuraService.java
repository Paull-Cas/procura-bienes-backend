package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.dto.request.ProcuraRequest;
import com.microservice.comprasycotizaciones.dto.response.OfertaCotizadaResponse;
import com.microservice.comprasycotizaciones.dto.response.ProcuraResponse;

import java.util.List;

public interface IProcuraService {

    void save(ProcuraRequest procuraRequest);

    List<ProcuraResponse> getAll(Long idUsuario);

    List<ProcuraResponse> getAllNoCotizadas(Long idUsuario);

    List<OfertaCotizadaResponse> getComparativo(Long idProcura);

    ProcuraResponse getById(Long id);

    void update(ProcuraRequest procuraRequest, Long idProcura);

    void delete(Long id);
}
