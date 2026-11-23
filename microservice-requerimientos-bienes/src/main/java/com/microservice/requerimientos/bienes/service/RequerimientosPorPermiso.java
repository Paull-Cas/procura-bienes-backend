package com.microservice.requerimientos.bienes.service;

import com.microservice.requerimientos.bienes.entities.Requerimiento;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class RequerimientosPorPermiso {

    public static List<Requerimiento> getRequerimientos(List<Requerimiento> requerimientos, Long idUsuario) {
        List<String> permisos = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        List<Requerimiento> requerimientosFiltrados = requerimientos.stream()
                .filter(r -> {
                    boolean puedeVerMateriales = permisos.contains("Ver Requerimientos de Materiales")
                            && "Obra".equals(r.getTipoRequerimiento());
                    boolean puedeVerHerramientas = permisos.contains("Ver Requerimientos de Herramientas")
                            && "Operación".equals(r.getTipoRequerimiento());
                    boolean puedeVerMis = permisos.contains("Ver Mis Requerimientos de Bienes")
                            && r.getIdUsuario().equals(idUsuario);

                    return puedeVerMateriales || puedeVerHerramientas || puedeVerMis;
                })
                .collect(Collectors.toList());

        return requerimientosFiltrados;
    }
}
