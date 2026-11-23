package com.microservice.requerimientos.bienes.repository;

import com.microservice.requerimientos.bienes.entities.Justificacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJustificacionRepository extends CrudRepository<Justificacion, Long> {
}
