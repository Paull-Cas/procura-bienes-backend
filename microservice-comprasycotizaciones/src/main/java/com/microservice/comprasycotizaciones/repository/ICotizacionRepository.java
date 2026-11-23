package com.microservice.comprasycotizaciones.repository;

import com.microservice.comprasycotizaciones.entities.Cotizacion;
import com.netflix.spectator.api.AbstractRegistry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICotizacionRepository extends CrudRepository<Cotizacion, Long> {
    Cotizacion getCotizacionByIdCotizacion(Long idCotizacion);


    List<Cotizacion> findCotizacionByIdUsuario(Long idUsuario);

    Cotizacion findCotizacionByIdUsuarioAndProcura_IdProcura(Long idUsuario, Long procuraIdProcura);
}
