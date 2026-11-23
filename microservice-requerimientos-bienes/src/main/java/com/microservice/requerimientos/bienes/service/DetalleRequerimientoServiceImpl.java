package com.microservice.requerimientos.bienes.service;

import com.microservice.requerimientos.bienes.dto.DetalleRequerimientoDTO;
import com.microservice.requerimientos.bienes.entities.Bien;
import com.microservice.requerimientos.bienes.entities.DetalleRequerimiento;
import com.microservice.requerimientos.bienes.entities.Requerimiento;
import com.microservice.requerimientos.bienes.mapper.DetalleRequerimientoMapper;
import com.microservice.requerimientos.bienes.repository.IBienRepository;
import com.microservice.requerimientos.bienes.repository.IDetalleRequerimientoRepository;
import com.microservice.requerimientos.bienes.repository.IRequerimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DetalleRequerimientoServiceImpl implements IDetalleRequerimientoService {

    @Autowired
    private IDetalleRequerimientoRepository detalleRequerimientoRepository;

    @Autowired
    private IRequerimientoRepository requerimientoRepository;

    @Autowired
    private IBienRepository bienRepository;


    @Transactional
    @Override
    public List<DetalleRequerimientoDTO> getAllByRequerimiento(Long idRequerimiento) {
        List<DetalleRequerimiento> detalles = detalleRequerimientoRepository.findDetalleRequerimientoByRequerimiento_IdRequerimiento(idRequerimiento);
        if (detalles != null && !detalles.isEmpty()) return DetalleRequerimientoMapper.toDTOList(detalles);
        else return null;
    }

    @Transactional
    @Override
    public DetalleRequerimientoDTO getById(Long id) {
        DetalleRequerimiento detalle = detalleRequerimientoRepository.findById(id).orElse(null);
        if (detalle != null) return DetalleRequerimientoMapper.toDTO(detalle);
        else return null;
    }

    @Override
    public void update(Long idRequerimiento, List<DetalleRequerimientoDTO> listaDTO) {
        if (listaDTO == null) {
            throw new RuntimeException("La lista de detalles está vacía o es nula");
        }

        Requerimiento requerimiento = requerimientoRepository.findById(idRequerimiento)
                .orElseThrow(() -> new RuntimeException("Requerimiento no encontrado con ID: " + idRequerimiento));

        // Obtener detalles existentes
        List<DetalleRequerimiento> existingDetalles = detalleRequerimientoRepository.findDetalleRequerimientoByRequerimiento_IdRequerimiento(idRequerimiento);

        // IDs de detalles existentes y DTOs
        Set<Long> existingIds = existingDetalles.stream()
                .map(DetalleRequerimiento::getIdDetalleRequerimiento)
                .collect(Collectors.toSet());
        Set<Long> dtoIds = listaDTO.stream()
                .map(DetalleRequerimientoDTO::getIdDetalleRequerimiento)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // Eliminar detalles no presentes en los DTOs
        List<DetalleRequerimiento> toDelete = existingDetalles.stream()
                .filter(d -> !dtoIds.contains(d.getIdDetalleRequerimiento()))
                .collect(Collectors.toList());
        detalleRequerimientoRepository.deleteAll(toDelete);

        // Procesar DTOs para actualizar o crear
        List<DetalleRequerimiento> toSave = new ArrayList<>();
        for (DetalleRequerimientoDTO dto : listaDTO) {
            Bien bien = bienRepository.findById(dto.getIdBien())
                    .orElseThrow(() -> new RuntimeException("Bien no encontrado con ID: " + dto.getIdBien()));

            if (dto.getIdDetalleRequerimiento() != null && existingIds.contains(dto.getIdDetalleRequerimiento())) {
                // Actualizar existente
                DetalleRequerimiento existente = existingDetalles.stream()
                        .filter(d -> d.getIdDetalleRequerimiento().equals(dto.getIdDetalleRequerimiento()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

                // Actualizar campos
                existente.setMarca(dto.getMarca());
                existente.setUnidadMedida(dto.getUnidadMedida());
                existente.setCaracteristicaTecnica(dto.getCaracteristicaTecnica());
                existente.setCantidad(dto.getCantidad());
                existente.setAplicaFichaTecnica(dto.isAplicaFichaTecnica());
                existente.setFichaTecnica(dto.getFichaTecnica());
                existente.setAplicaCertificadoCalidad(dto.isAplicaCertificadoCalidad());
                existente.setCertificadoCalidad(dto.getCertificadoCalidad());
                existente.setBien(bien);

                toSave.add(existente);
            } else {
                DetalleRequerimiento nuevo = DetalleRequerimientoMapper.toEntity(dto, requerimiento, bien);
                toSave.add(nuevo);
            }
        }

        detalleRequerimientoRepository.saveAll(toSave);
    }


    @Override
    public void deleteById(Long id) {
        if (detalleRequerimientoRepository.existsById(id)) {
            detalleRequerimientoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No existe el detalle con el id: " + id);
        }
    }
}
