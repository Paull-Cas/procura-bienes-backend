package com.microservice.requerimientos.bienes.service;

import com.microservice.requerimientos.bienes.dto.DetalleRequerimientoDTO;

import java.util.List;

public interface IDetalleRequerimientoService {
    List<DetalleRequerimientoDTO> getAllByRequerimiento(Long idRequerimiento);
    DetalleRequerimientoDTO getById(Long id);
    void update(Long idRequerimiento,List<DetalleRequerimientoDTO> listaDTO);
    void deleteById(Long id);
}
