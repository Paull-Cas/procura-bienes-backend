package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.entities.Procura;
import com.microservice.comprasycotizaciones.repository.IProcuraRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
public class ProcuraSchedulerService {

    @Autowired
    private IProcuraRepository procuraRepository;

    @Scheduled(cron = "0 0 0 * * *", zone = "America/Lima")
    public void actualizarEstadosProcurasVencidas(){
        LocalDate hoy = LocalDate.now(ZoneId.of("America/Lima")); // ← Usar hora de Perú

        log.info("Ejecutando actualización a las: {}",
                ZonedDateTime.now(ZoneId.of("America/Lima")));

        List<Procura> procurasVencidas = procuraRepository
                .findByFechaMaximaCotizacionBeforeAndEstado(hoy.plusDays(1), "Activo");

        procurasVencidas.forEach(procura -> procura.setEstado("Vencido"));
        procuraRepository.saveAll(procurasVencidas);

        log.info("Actualizadas {} procuras vencidas", procurasVencidas.size());
    }
}
