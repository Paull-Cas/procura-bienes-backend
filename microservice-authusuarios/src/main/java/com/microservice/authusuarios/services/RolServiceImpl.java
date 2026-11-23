package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.request.RolRequest;
import com.microservice.authusuarios.dto.response.RolResponse;
import com.microservice.authusuarios.entities.Rol;
import com.microservice.authusuarios.mapper.RolMapper;
import com.microservice.authusuarios.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private IRolPermisoService rolPermisoService;

    @Override
    public void saveRol(RolRequest rol, List<Long> idPermisos) {
        Rol rolSaved = rolRepository.save(RolMapper.toEntity(rol));
        rolPermisoService.saveRolPermiso(rolSaved, idPermisos);
    }

    @Override
    public List<RolResponse> getAllRols() {
        List<Rol> roles = rolRepository.findAll();
        return RolMapper.toResponseList(roles);
    }


    @Override
    public void updateRol(Long rolId, RolRequest rol, List<Long> idPermisos) {
        if (rolRepository.existsById(rolId)) {
            Rol rolUpdate = RolMapper.toEntity(rol);
            rolUpdate.setIdRol(rolId);
            rolRepository.save(rolUpdate);
            rolPermisoService.updateRolPermiso(rolUpdate, idPermisos);
        } else {
            throw new RuntimeException("Rol no encontrado con ID: " + rolId);
        }
    }

    @Override
    public void deleteRol(Long rolId) {
        if (rolRepository.existsById(rolId)) {
            rolRepository.deleteById(rolId);
        }
        else throw new RuntimeException("Rol no encontrado, id: " + rolId);
    }
}
