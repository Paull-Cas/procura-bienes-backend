package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.response.PermisoResponse;
import com.microservice.authusuarios.dto.response.RolPermisoResponse;
import com.microservice.authusuarios.dto.response.RolResponse;
import com.microservice.authusuarios.entities.Permiso;
import com.microservice.authusuarios.entities.Rol;
import com.microservice.authusuarios.entities.RolPermiso;
import com.microservice.authusuarios.mapper.PermisoMapper;
import com.microservice.authusuarios.mapper.RolMapper;
import com.microservice.authusuarios.mapper.RolPermisoMapper;
import com.microservice.authusuarios.repository.IPermisoRepository;
import com.microservice.authusuarios.repository.IRolPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RolPermisoServiceImpl implements IRolPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;

    @Autowired
    private IRolPermisoRepository rolPermisoRepository;

    @Override
    public void saveRolPermiso(Rol rol, List<Long> idPermisos) {
        List<RolPermiso> entidadesParaGuardar = idPermisos.stream()
                .map(idPermiso -> {
                    Permiso permiso = permisoRepository.findById(idPermiso)
                            .orElseThrow(() -> new RuntimeException("Permiso no encontrado con ID: " + idPermiso));

                    return RolPermisoMapper.toEntity(rol, permiso);
                })
                .collect(Collectors.toList());

        rolPermisoRepository.saveAll(entidadesParaGuardar);
    }

    @Override
    public List<RolPermisoResponse> getPermisosPorRol() {
        List<RolPermiso> rolPermisos = rolPermisoRepository.findAll();

        Map<RolResponse, List<PermisoResponse>> permisosPorRol = rolPermisos.stream()
                .collect(Collectors.groupingBy(
                        rolPermiso -> RolMapper.toResponse(rolPermiso.getRol()),
                        Collectors.mapping(
                                rolPermiso -> PermisoMapper.toResponse(rolPermiso.getPermiso()),
                                Collectors.toList()
                        )
                ));

        List<RolPermisoResponse> rolPermisoResponses = permisosPorRol.entrySet().stream()
                .map(entry -> RolPermisoMapper.toResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return rolPermisoResponses;
    }

    @Override
    public void updateRolPermiso(Rol rol, List<Long> idPermisos) {
        List<RolPermiso> existingRolPermisos = rolPermisoRepository.getRolPermisoByRol_IdRol(rol.getIdRol());

        Set<Long> existingPermisoIds = existingRolPermisos.stream()
                .map(rolPermiso -> rolPermiso.getPermiso().getIdPermiso())
                .collect(Collectors.toSet());

        Set<Long> newPermisoIds = new HashSet<>(idPermisos);

        List<RolPermiso> toDelete = existingRolPermisos.stream()
                .filter(rolPermiso -> !newPermisoIds.contains(rolPermiso.getPermiso().getIdPermiso()))
                .collect(Collectors.toList());

        List<Long> permisosToAdd = newPermisoIds.stream()
                .filter(idPermiso -> !existingPermisoIds.contains(idPermiso))
                .collect(Collectors.toList());

        rolPermisoRepository.deleteAll(toDelete);
        if (!permisosToAdd.isEmpty()) {
            List<RolPermiso> toSave = permisosToAdd.stream()
                    .map(idPermiso -> {
                        Permiso permiso = permisoRepository.findById(idPermiso)
                                .orElseThrow(() -> new RuntimeException("Permiso no encontrado con ID: " + idPermiso));
                        return RolPermisoMapper.toEntity(rol, permiso);
                    })
                    .collect(Collectors.toList());

            rolPermisoRepository.saveAll(toSave);
        }
    }
}