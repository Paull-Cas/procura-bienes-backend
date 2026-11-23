package com.microservice.transacciones.repository;

import com.microservice.transacciones.entities.Factura;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFacturaRepository extends CrudRepository<Factura, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT f.titulo FROM Factura f WHERE f.idUsuario = :usuario ORDER BY f.idFactura DESC LIMIT 1")
    Optional<String> findLastTituloByUsuarioLocked(@Param("usuario") Long usuario);
}
