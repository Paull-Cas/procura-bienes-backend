package com.microservice.requerimientos.bienes.dto.request;

import com.microservice.requerimientos.bienes.dto.DetalleRequerimientoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequerimientoConBienesRequest {
    private RequerimientoRequest requerimiento;
    private List<DetalleRequerimientoDTO> bienes;
}