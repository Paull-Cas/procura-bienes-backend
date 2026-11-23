package com.microservice.requerimientos.bienes.service;

import com.microservice.requerimientos.bienes.dto.JustificacionDTO;

import java.util.List;

public interface IJustificacionService {
    void save(JustificacionDTO justificacionDTO);
    List<JustificacionDTO> getAll();
    JustificacionDTO getById(Long id);
    void update(JustificacionDTO justificacionDTO, Long id);
    void deleteById(Long id);
}
