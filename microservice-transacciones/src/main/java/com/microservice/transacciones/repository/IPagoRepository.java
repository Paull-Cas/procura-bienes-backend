package com.microservice.transacciones.repository;

import com.microservice.transacciones.entities.Pago;
import feign.Param;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

@Repository
public interface IPagoRepository extends CrudRepository<Pago, Long> {

    List<Pago> findPagoByFactura_IdFactura(Long facturaIdFactura);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p.titulo FROM Pago p WHERE p.factura.idFactura = :idFactura ORDER BY p.idPago DESC LIMIT 1")
    Optional<String> findLastTituloByFacturaLocked(@Param("idFactura") Long idFactura);

}
