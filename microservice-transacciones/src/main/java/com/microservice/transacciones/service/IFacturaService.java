package com.microservice.transacciones.service;

import com.microservice.transacciones.dto.request.FacturaRequest;
import com.microservice.transacciones.dto.response.FacturaResponse;

import java.math.BigDecimal;
import java.util.List;

public interface IFacturaService {
    void save(FacturaRequest factura);
    List<FacturaResponse> getAll(Long idUsuario);
    FacturaResponse getById(Long id);
    void update(FacturaRequest factura, Long id);
    void updatePago(BigDecimal monto_actualizado, Long id);
    void deleteById(Long id);
}
