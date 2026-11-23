package com.microservice.authusuarios.repository;

import com.microservice.authusuarios.entities.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermisoRepository extends JpaRepository<Permiso, Long> {

    @Query(
            value = "CALL ObtenerPermisosPorEmail(:email)",
            nativeQuery = true
    )
    List<Permiso> getPermisosByUser(@Param("email") String email);

}
