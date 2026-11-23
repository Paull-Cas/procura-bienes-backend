package com.microservice.transacciones.service;

import com.microservice.transacciones.dto.request.PagoRequest;
import com.microservice.transacciones.dto.response.PagoResponse;

import java.util.List;

public interface IPagoService {
    void save(PagoRequest pago);
    List<PagoResponse> getAll();
    List<PagoResponse> getListByIdFactura(Long idFactura);
    PagoResponse getById(Long id);
    void update(PagoRequest pago);
    void deleteById(Long id);
}
