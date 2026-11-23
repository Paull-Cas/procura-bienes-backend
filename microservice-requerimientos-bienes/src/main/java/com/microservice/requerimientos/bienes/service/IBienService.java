package com.microservice.requerimientos.bienes.service;

import com.microservice.requerimientos.bienes.dto.BienDTO;

import java.util.List;

public interface IBienService {
    void save(BienDTO bienDTO);
    List<BienDTO> getAll();
    BienDTO getById(Long id);
    void update(BienDTO bienDTO, Long id);
    void deleteById(Long id);
}