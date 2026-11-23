package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.dto.request.CotizacionConBienesRequest;
import com.microservice.comprasycotizaciones.dto.request.CotizacionRequest;
import com.microservice.comprasycotizaciones.dto.request.DetalleCotizacionRequest;
import com.microservice.comprasycotizaciones.dto.response.CotizacionResponse;
import com.microservice.comprasycotizaciones.entities.Cotizacion;
import com.microservice.comprasycotizaciones.entities.DetalleCotizacion;
import com.microservice.comprasycotizaciones.entities.Procura;
import com.microservice.comprasycotizaciones.mapper.CotizacionMapper;
import com.microservice.comprasycotizaciones.mapper.DetalleCotizacionMapper;
import com.microservice.comprasycotizaciones.repository.ICotizacionRepository;
import com.microservice.comprasycotizaciones.repository.IDetalleCotizacionRepository;
import com.microservice.comprasycotizaciones.repository.IProcuraRepository;
import com.microservice.comprasycotizaciones.security.CustomAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class CotizacionServiceImpl implements ICotizacionService {

    @Autowired
    ICotizacionRepository cotizacionRepository;

    @Autowired
    IProcuraRepository procuraRepository;

    @Autowired
    IDetalleCotizacionRepository detalleCotizacionRepository;

    @Override
    public String save(CotizacionConBienesRequest cotizacionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String alias = ((CustomAuthenticationToken) authentication).getAlias();

        Procura procura = procuraRepository.getProcuraByIdProcura(cotizacionRequest.getCotizacion().getIdProcura());
        Cotizacion cotizacion = CotizacionMapper.toEntity(cotizacionRequest.getCotizacion(), procura);
        String titulo = GenerarTitulo.toCotizacion(procura.getCodigoProcura(), alias.toUpperCase());
        cotizacion.setCodigoCotizacion(titulo);
        cotizacion = cotizacionRepository.save(cotizacion);
        guardarDetalles(cotizacionRequest.getBienescotizados(), cotizacion);
        return titulo;
    }

    private void guardarDetalles(List<DetalleCotizacionRequest> detalles, Cotizacion cotizacion) {
        List<DetalleCotizacion> entidades = detalles.stream()
                .map(dto -> DetalleCotizacionMapper.toEntity(dto, cotizacion))
                .collect(Collectors.toList());
        detalleCotizacionRepository.saveAll(entidades);
    }

    @Override
    public List<CotizacionResponse> getAll(Long idUsuario) {
        List<Cotizacion> cotizaciones = FiltroPorPermisos.getCotizaciones(StreamSupport
                .stream(cotizacionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()), idUsuario);
        return CotizacionMapper.toListResponse(cotizaciones);
    }

    @Override
    public CotizacionResponse getById(Long id) {
        Cotizacion cotizacion = cotizacionRepository.findById(id).orElse(null);
        return CotizacionMapper.toResponse(cotizacion);
    }

    @Override
    public CotizacionResponse getByProcuraProveedor(Long idUsuario, Long idProcura) {
        Cotizacion cotizacion = cotizacionRepository.findCotizacionByIdUsuarioAndProcura_IdProcura(idUsuario, idProcura);
        System.out.println("Cotizacion " + cotizacion);
        return CotizacionMapper.toResponse(cotizacion);
    }

    @Override
    public void update(CotizacionRequest cotizacion, Long id) {
        if (cotizacionRepository.existsById(id)) {
            Cotizacion cotizacion1 = cotizacionRepository.findById(id).orElse(null);
            Cotizacion uCotizacion = CotizacionMapper.toEntity(cotizacion, procuraRepository.findById(cotizacion.getIdProcura()).get());
            uCotizacion.setIdCotizacion(id);
            uCotizacion.setCodigoCotizacion(cotizacion1.getCodigoCotizacion());
            cotizacionRepository.save(uCotizacion);
        }
        else throw new RuntimeException("Cotizacion no encontrada");
    }

    @Override
    public void delete(Long id) {
        if (cotizacionRepository.existsById(id)) cotizacionRepository.deleteById(id);
        else throw new RuntimeException("Cotizacion no encontrada");
    }
}
