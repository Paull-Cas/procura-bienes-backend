package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.entities.Compra;
import com.microservice.comprasycotizaciones.entities.Cotizacion;
import com.microservice.comprasycotizaciones.entities.Procura;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroPorPermisos {

    public static List<Procura> getProcuras(List<Procura> procuras, Long idUsuario) {
        List<String> permisos = getPermisos();
        List<Procura> procurasFiltradas = procuras.stream()
                .filter(r -> {
                    boolean puedeVerMateriales = permisos.contains("Ver Procuras de Materiales")
                            && "Obra".equals(r.getTipoRequerimiento());
                    boolean puedeVerHerramientas = permisos.contains("Ver Procuras de Herramientas")
                            && "Operación".equals(r.getTipoRequerimiento());
                    boolean puedeVerMis = permisos.contains("Ver Mis Procuras de Bienes")
                            && r.getIdUsuario().equals(idUsuario);

                    return puedeVerMateriales || puedeVerHerramientas || puedeVerMis;
                })
                .collect(Collectors.toList());
        return procuras;
    }

    public static List<Cotizacion> getCotizaciones(List<Cotizacion> cotizaciones, Long idUsuario) {
        List<String> permisos = getPermisos();
        List<Cotizacion> cotizacionesFiltradas = cotizaciones.stream()
                .filter(r -> {
                    boolean puedeVerMateriales = permisos.contains("Ver Cotizaciones de Materiales")
                            && "Obra".equals(r.getProcura().getTipoRequerimiento());
                    boolean puedeVerHerramientas = permisos.contains("Ver Cotizaciones de Herramientas")
                            && "Operación".equals(r.getProcura().getTipoRequerimiento());
                    boolean puedeVerMis = permisos.contains("Ver Mis Cotizaciones de Bienes")
                            && r.getIdUsuario().equals(idUsuario);

                    return puedeVerMateriales || puedeVerHerramientas || puedeVerMis;
                })
                .collect(Collectors.toList());
        return cotizacionesFiltradas;
    }

    public static List<Compra> getCompras(List<Compra> compras, Long idUsuario) {
        List<String> permisos = getPermisos();
        List<Compra> comprasFiltradas = compras.stream()
                .filter(c -> {
                    boolean puedeVerMateriales = permisos.contains("Ver Compras de Materiales")
                            && "Obra".equals(c.getCotizacion().getProcura().getTipoRequerimiento());
                    boolean puedeVerHerramientas = permisos.contains("Ver Compras de Herramientas")
                            && "Operación".equals(c.getCotizacion().getProcura().getTipoRequerimiento());
                    boolean puedeVerMis = permisos.contains("Ver Mis Compras de Bienes")
                            && c.getCotizacion().getIdUsuario().equals(idUsuario);

                    return puedeVerMateriales || puedeVerHerramientas || puedeVerMis;
                })
                .collect(Collectors.toList());
        return comprasFiltradas;
    }

    private static List<String> getPermisos(){
        List<String> permisos = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return permisos;
    }
}
