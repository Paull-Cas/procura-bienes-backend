package com.microservice.authusuarios.repository;

import com.microservice.authusuarios.entities.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IModuloRepository extends JpaRepository<Modulo, Long> {
    @Query(
            value = "CALL obtenerModulosPorPermiso(:permiso)",
            nativeQuery = true
    )
    List<Modulo> obtenerModuloAsociadoAPermiso(@Param("permiso") String permiso);
}
