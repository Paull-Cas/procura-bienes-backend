package com.microservice.authusuarios.repository;

import com.microservice.authusuarios.entities.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRolPermisoRepository extends JpaRepository<RolPermiso, Long> {
    List<RolPermiso> getRolPermisoByRol_IdRol(Long rolIdRol);
}
