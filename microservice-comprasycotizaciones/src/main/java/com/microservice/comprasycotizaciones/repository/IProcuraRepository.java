package com.microservice.comprasycotizaciones.repository;

import com.microservice.comprasycotizaciones.entities.Procura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IProcuraRepository extends CrudRepository<Procura, Long> {
    Procura getProcuraByIdProcura(Long idProcura);

    List<Procura> findByFechaMaximaCotizacionBeforeAndEstado(LocalDate hoy, String publicado);
}
