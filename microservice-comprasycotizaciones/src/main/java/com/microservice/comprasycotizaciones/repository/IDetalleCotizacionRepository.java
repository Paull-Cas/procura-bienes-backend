package com.microservice.comprasycotizaciones.repository;

import com.microservice.comprasycotizaciones.entities.DetalleCotizacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalleCotizacionRepository extends CrudRepository<DetalleCotizacion, Long> {
    List<DetalleCotizacion> findByCotizacionIdCotizacion(Long idCotizacion);

    DetalleCotizacion getByIdDetalleCotizacion(Long idDetalleCotizacion);

    List<DetalleCotizacion> findByCotizacion_Procura_IdProcuraAndEstado(Long idProcura, String estado);
}
