package com.microservice.comprasycotizaciones.repository;

import com.microservice.comprasycotizaciones.entities.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompraRepository extends CrudRepository<Compra, Long> {

    long countByCotizacion_Procura_IdProcura(Long idProcura);

    List<Compra> findByCotizacion_Procura_IdProcuraAndEstado(Long idProcura, String estado);

    List<Compra> findByCotizacion_IdUsuario(Long idUsuario);
}
