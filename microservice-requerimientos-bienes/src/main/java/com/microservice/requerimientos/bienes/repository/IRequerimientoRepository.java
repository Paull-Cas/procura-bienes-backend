package com.microservice.requerimientos.bienes.repository;

import com.microservice.requerimientos.bienes.entities.Requerimiento;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRequerimientoRepository extends CrudRepository<Requerimiento, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT COUNT(r) FROM Requerimiento r WHERE r.idUsuario = :usuario AND r.tipoRequerimiento = :tipo")
    int countByUsuarioAndTipoLocked(
            @Param("usuario") Long usuario,
            @Param("tipo") String tipo
    );

    Requerimiento getByTitulo(String titulo);
}
