package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.dto.request.DetalleCotizacionRequest;
import com.microservice.comprasycotizaciones.dto.request.EvaluacionDetallCotizacionRequest;
import com.microservice.comprasycotizaciones.dto.response.DetalleCotizacionResponse;
import com.microservice.comprasycotizaciones.entities.Cotizacion;
import com.microservice.comprasycotizaciones.entities.DetalleCotizacion;
import com.microservice.comprasycotizaciones.mapper.DetalleCotizacionMapper;
import com.microservice.comprasycotizaciones.repository.ICotizacionRepository;
import com.microservice.comprasycotizaciones.repository.IDetalleCotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DetalleCotizacionServiceImpl implements IDetalleCotizacionService {

    @Autowired
    private ICotizacionRepository cotizacionRepository;

    @Autowired
    private IDetalleCotizacionRepository detalleCotizacionRepository;

    @Override
    public List<DetalleCotizacionResponse> getAllByCotizacion(Long idCotizacion) {
        List<DetalleCotizacion> detalles = detalleCotizacionRepository.findByCotizacionIdCotizacion(idCotizacion);
        return DetalleCotizacionMapper.toListResponse(detalles);
    }

    @Override
    public DetalleCotizacionResponse getById(Long idDetalleCotizacion) {
        DetalleCotizacion detalle = detalleCotizacionRepository.findById(idDetalleCotizacion).orElse(null);
        if (detalle != null) {
            return DetalleCotizacionMapper.toResponse(detalle);
        }
        return null;
    }

    @Override
    public void update(Long idCotizacion, List<DetalleCotizacionRequest> listaItems) {
        if (listaItems == null) {
            throw new RuntimeException("La lista de detalles está vacía o es nula");
        }

        Cotizacion cotizacion = cotizacionRepository.findById(idCotizacion)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada con ID: " + idCotizacion));

        // Obtener detalles existentes
        List<DetalleCotizacion> existingDetalles = detalleCotizacionRepository.findByCotizacionIdCotizacion(idCotizacion);

        // IDs de detalles existentes y DTOs
        Set<Long> existingIds = existingDetalles.stream()
                .map(DetalleCotizacion::getIdDetalleCotizacion)
                .collect(Collectors.toSet());
        Set<Long> dtoIds = listaItems.stream()
                .map(DetalleCotizacionRequest::getIdDetalleCotizacion)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // Eliminar detalles no presentes en los DTOs
        List<DetalleCotizacion> toDelete = existingDetalles.stream()
                .filter(d -> !dtoIds.contains(d.getIdDetalleCotizacion()))
                .collect(Collectors.toList());
        detalleCotizacionRepository.deleteAll(toDelete);

        // Procesar DTOs para actualizar o crear
        List<DetalleCotizacion> toSave = new ArrayList<>();
        for (DetalleCotizacionRequest dto : listaItems) {
            if (dto.getIdDetalleCotizacion() != null && existingIds.contains(dto.getIdDetalleCotizacion())) {
                // Actualizar existente
                DetalleCotizacion existente = existingDetalles.stream()
                        .filter(d -> d.getIdDetalleCotizacion().equals(dto.getIdDetalleCotizacion()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

                // Actualizar campos
                existente.setIdDetalleRequerimiento(dto.getIdDetalleRequerimiento());
                existente.setCantidad(dto.getCantidad());
                existente.setPrecioUnitario(dto.getPrecioUnitario());
                existente.setEstado(dto.getEstado());
                existente.setFichaTecnica(dto.getFichaTecnica());
                existente.setBrindaCertificadoCalidad(dto.isBrindaCertificadoCalidad());

                toSave.add(existente);
            } else {
                // Crear nuevo usando el mapper
                DetalleCotizacion nuevo = DetalleCotizacionMapper.toEntity(dto, cotizacion);
                toSave.add(nuevo);
            }
        }

        detalleCotizacionRepository.saveAll(toSave);
    }

    @Override
    public void deleteById(Long id) {
        if (detalleCotizacionRepository.existsById(id)) {
            detalleCotizacionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Detalle de cotización no encontrado");
        }
    }

    @Override
    public void evaluarDetalleCotizacion(Long idCotizacion, List<EvaluacionDetallCotizacionRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            return;
        }
        else {
            Cotizacion cotizacion = cotizacionRepository.findById(idCotizacion).orElse(null);
            cotizacion.setEstado("Evaluado");
            cotizacionRepository.save(cotizacion);

            requests.stream().forEach(r -> {
                DetalleCotizacion detalleCotizacion = detalleCotizacionRepository.getByIdDetalleCotizacion(r.getIdDetalleCotizacion());
                detalleCotizacion.setEstado(r.getEstado());
                detalleCotizacionRepository.save(detalleCotizacion);
            });
        }
    }
}