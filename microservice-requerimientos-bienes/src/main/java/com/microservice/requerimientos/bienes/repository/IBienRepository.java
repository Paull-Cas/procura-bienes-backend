package com.microservice.requerimientos.bienes.repository;

import com.microservice.requerimientos.bienes.entities.Bien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBienRepository extends CrudRepository<Bien, Long> {
}
