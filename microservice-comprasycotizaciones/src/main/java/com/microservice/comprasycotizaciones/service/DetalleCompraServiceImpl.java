package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.dto.request.DetalleCompraRequest;
import com.microservice.comprasycotizaciones.dto.response.DetalleCompraResponse;
import com.microservice.comprasycotizaciones.entities.Compra;
import com.microservice.comprasycotizaciones.entities.DetalleCompra;
import com.microservice.comprasycotizaciones.entities.DetalleCotizacion;
import com.microservice.comprasycotizaciones.mapper.DetalleCompraMapper;
import com.microservice.comprasycotizaciones.repository.ICompraRepository;
import com.microservice.comprasycotizaciones.repository.IDetalleCompraRepository;
import com.microservice.comprasycotizaciones.repository.IDetalleCotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DetalleCompraServiceImpl implements IDetalleCompraService {

    @Autowired
    private IDetalleCompraRepository detalleCompraRepository;

    @Autowired
    private ICompraRepository compraRepository;

    @Autowired
    private IDetalleCotizacionRepository detalleCotizacionRepository;

    @Override
    public List<DetalleCompraResponse> getAllByCompra(Long idCompra) {
        List<DetalleCompra> detalles = detalleCompraRepository.findByCompraIdCompra(idCompra);
        return DetalleCompraMapper.toResponse(detalles);
    }

    @Override
    public DetalleCompraResponse getById(Long idDetalleCompra) {
        DetalleCompra detalle = detalleCompraRepository.findById(idDetalleCompra).orElse(null);
        if (detalle != null) {
            return DetalleCompraMapper.toResponse(detalle);
        }
        return null;
    }

    @Override
    public void update(Long idCompra, List<DetalleCompraRequest> listaItems) {
        if (listaItems == null) {
            throw new RuntimeException("La lista de detalles está vacía o es nula");
        }

        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + idCompra));

        // Obtener detalles existentes
        List<DetalleCompra> existingDetalles = detalleCompraRepository.findByCompraIdCompra(idCompra);

        // IDs de detalles existentes y DTOs
        Set<Long> existingIds = existingDetalles.stream()
                .map(DetalleCompra::getIdDetalleCompra)
                .collect(Collectors.toSet());
        Set<Long> dtoIds = listaItems.stream()
                .map(DetalleCompraRequest::getIdDetalleCompra)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // Eliminar detalles no presentes en los DTOs
        List<DetalleCompra> toDelete = existingDetalles.stream()
                .filter(d -> !dtoIds.contains(d.getIdDetalleCompra()))
                .collect(Collectors.toList());
        detalleCompraRepository.deleteAll(toDelete);

        // Procesar DTOs para actualizar o crear
        List<DetalleCompra> toSave = new ArrayList<>();
        for (DetalleCompraRequest dto : listaItems) {
            // Obtener detalle cotización
            DetalleCotizacion detalleCotizacion = detalleCotizacionRepository.findById(dto.getIdDetalleCotizacion())
                    .orElseThrow(() -> new RuntimeException("Detalle cotización no encontrado con ID: " + dto.getIdDetalleCotizacion()));

            if (dto.getIdDetalleCompra() != null && existingIds.contains(dto.getIdDetalleCompra())) {
                // Actualizar existente
                DetalleCompra existente = existingDetalles.stream()
                        .filter(d -> d.getIdDetalleCompra().equals(dto.getIdDetalleCompra()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

                // Actualizar campos
                existente.setDetalleCotizacion(detalleCotizacion);
                existente.setCantidadComprada(dto.getCantidadComprada());
                existente.setPrecioCompra(dto.getPrecioCompra());
                existente.setObservaciones(dto.getObservaciones());

                toSave.add(existente);
            } else {
                // Crear nuevo usando el mapper
                DetalleCompra nuevo = DetalleCompraMapper.toEntity(dto, compra, detalleCotizacion);
                toSave.add(nuevo);
            }
        }

        detalleCompraRepository.saveAll(toSave);
    }

    @Override
    public void deleteById(Long id) {
        if (detalleCompraRepository.existsById(id)) {
            detalleCompraRepository.deleteById(id);
        } else {
            throw new RuntimeException("Detalle de compra no encontrado");
        }
    }
}