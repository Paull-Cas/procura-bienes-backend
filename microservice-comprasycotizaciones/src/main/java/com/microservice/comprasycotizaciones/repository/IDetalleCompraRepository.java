package com.microservice.comprasycotizaciones.repository;

import com.microservice.comprasycotizaciones.entities.DetalleCompra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalleCompraRepository extends CrudRepository<DetalleCompra, Long> {
    List<DetalleCompra> findByCompraIdCompra(Long idCompra);

    // Detalles de compra de todas las órdenes de una procura (para saber qué ítems ya se compraron)
    List<DetalleCompra> findByCompra_Cotizacion_Procura_IdProcura(Long idProcura);
}
