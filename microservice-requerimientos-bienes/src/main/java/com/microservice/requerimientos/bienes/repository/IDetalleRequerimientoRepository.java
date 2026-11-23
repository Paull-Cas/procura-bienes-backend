package com.microservice.requerimientos.bienes.repository;

import com.microservice.requerimientos.bienes.entities.DetalleRequerimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalleRequerimientoRepository extends CrudRepository<DetalleRequerimiento, Long> {
    List<DetalleRequerimiento> findDetalleRequerimientoByRequerimiento_IdRequerimiento(Long requerimientoIdRequerimiento);
}
