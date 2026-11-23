package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.response.PermisoResponse;
import com.microservice.authusuarios.entities.Permiso;
import com.microservice.authusuarios.mapper.PermisoMapper;
import com.microservice.authusuarios.repository.IPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoServiceImpl implements IPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;

    @Override
    public List<PermisoResponse> getAllPermisos() {
        List<Permiso> permisos = permisoRepository.findAll();
        return PermisoMapper.toResponseList(permisos);
    }

    @Override
    public PermisoResponse getById(Long id) {
        if (permisoRepository.existsById(id)) {
            return PermisoMapper.toResponse(permisoRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public List<PermisoResponse> getPermisosByRol(Long idRol) {
        return List.of();
    }
}
