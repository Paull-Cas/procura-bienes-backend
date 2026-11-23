package com.microservice.requerimientos.bienes.service;

import com.microservice.requerimientos.bienes.dto.JustificacionDTO;
import com.microservice.requerimientos.bienes.entities.Justificacion;
import com.microservice.requerimientos.bienes.entities.Requerimiento;
import com.microservice.requerimientos.bienes.mapper.JustificacionMapper;
import com.microservice.requerimientos.bienes.repository.IJustificacionRepository;
import com.microservice.requerimientos.bienes.repository.IRequerimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JustificacionServiceImpl implements IJustificacionService {

    @Autowired
    IJustificacionRepository justificacionRepository;

    @Autowired
    IRequerimientoRepository requerimientoRepository;


    @Override
    public void save(JustificacionDTO dto) {
        Requerimiento req = requerimientoRepository.findById(dto.getIdRequerimiento()).orElse(null);
        if (req == null) throw new RuntimeException("No se encontró el requerimiento con id: " + dto.getIdRequerimiento());
        else justificacionRepository.save(JustificacionMapper.toEntity(dto, req));
    }

    @Transactional
    @Override
    public List<JustificacionDTO> getAll() {
        List<Justificacion> justificaciones = StreamSupport
                .stream(justificacionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        if (!justificaciones.isEmpty()) return JustificacionMapper.toDTOList(justificaciones);
        else return null;
    }

    @Override
    public JustificacionDTO getById(Long id) {
        Justificacion justificacion = justificacionRepository.findById(id).orElse(null);
        if (justificacion != null) return JustificacionMapper.toDTO(justificacion);
        else return null;
    }

    @Override
    public void update(JustificacionDTO dto, Long id) {
        Requerimiento req = requerimientoRepository.findById(dto.getIdRequerimiento()).orElse(null);
        if (justificacionRepository.findById(id).orElse(null) != null && (req != null)) {
            Justificacion justificacion = JustificacionMapper.toEntity(dto, req);
            justificacion.setIdJustificacion(id);
            justificacionRepository.save(justificacion);
        }
        else throw new RuntimeException("No existe la justificacion");
    }

    @Override
    public void deleteById(Long id) {
        if (justificacionRepository.findById(id).orElse(null) != null) justificacionRepository.deleteById(id);
        else throw new RuntimeException("No existe la justificacion");
    }
}