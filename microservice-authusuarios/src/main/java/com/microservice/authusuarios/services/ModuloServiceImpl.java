package com.microservice.authusuarios.services;

import com.microservice.authusuarios.dto.response.ModuloResponse;
import com.microservice.authusuarios.entities.Modulo;
import com.microservice.authusuarios.mapper.ModuloMapper;
import com.microservice.authusuarios.repository.IModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuloServiceImpl implements IModuloService {

    @Autowired
    private IModuloRepository moduloRepository;

    public List<ModuloResponse> obtenerModulosPorPermisos(List<String> permisos) {
        List<ModuloResponse> modulosr = new ArrayList<>();

        for (String permiso : permisos) {
            List<Modulo> modulos = moduloRepository.obtenerModuloAsociadoAPermiso(permiso);
            for (Modulo modulo : modulos) {
                ModuloResponse dto = ModuloMapper.toDTO(modulo);
                if (!modulosr.contains(dto)) {
                    modulosr.add(dto);
                }
            }
        }
        return modulosr;
    }

    @Override
    public List<ModuloResponse> getAll() {
        List<Modulo> modulos = moduloRepository.findAll();
        return ModuloMapper.toDTOList(modulos);
    }
}