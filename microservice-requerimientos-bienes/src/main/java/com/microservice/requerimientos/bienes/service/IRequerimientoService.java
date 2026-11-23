package com.microservice.requerimientos.bienes.service;

import com.microservice.requerimientos.bienes.dto.RequerimientoDTO;
import com.microservice.requerimientos.bienes.dto.request.RequerimientoConBienesRequest;
import com.microservice.requerimientos.bienes.dto.request.RequerimientoRequest;
import com.microservice.requerimientos.bienes.dto.response.TargetResponse;

import java.util.List;

public interface IRequerimientoService {
    List<String> save(RequerimientoConBienesRequest requerimientoDTO);

    List<TargetResponse> getAll(Long idUsuario);

    RequerimientoDTO getById(Long id);

    RequerimientoDTO getByTitulo(String titulo);

    void update(RequerimientoRequest requerimientoDTO, Long id);

    void deleteById(Long id);
}
