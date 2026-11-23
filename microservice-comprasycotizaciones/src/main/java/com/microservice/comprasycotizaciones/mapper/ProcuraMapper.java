package com.microservice.comprasycotizaciones.mapper;

import com.microservice.comprasycotizaciones.dto.request.ProcuraRequest;
import com.microservice.comprasycotizaciones.dto.response.ProcuraResponse;
import com.microservice.comprasycotizaciones.entities.Procura;

import java.util.List;
import java.util.stream.Collectors;

public class ProcuraMapper {

    public static Procura toEntity(ProcuraRequest request){
        return Procura.builder()
                .codigoProcura(request.getCodigoProcura())
                .idUsuario(request.getIdUsuario())
                .tipoRequerimiento(request.getTipoRequerimiento())
                .estado(request.getEstado())
                .fechaMaximaCotizacion(request.getFechaMaximaCotizacion())
                .fechaMaximaAbastecimiento(request.getFechaMaximaAbastecimiento())
                .build();
    }

    public static ProcuraResponse toResponse(Procura procura){
        return ProcuraResponse.builder()
                .idProcura(procura.getIdProcura())
                .codigoProcura(procura.getCodigoProcura())
                .idUsuario(procura.getIdUsuario())
                .tipoRequerimiento(procura.getTipoRequerimiento())
                .estado(procura.getEstado())
                .fechaMaximaCotizacion(procura.getFechaMaximaCotizacion())
                .fechaMaximaAbastecimiento(procura.getFechaMaximaAbastecimiento())
                .build();
    }

    public static List<ProcuraResponse> toListResponse(List<Procura> procuras){
        return procuras.stream()
                .map(ProcuraMapper::toResponse)
                .collect(Collectors.toList());
    }
}
