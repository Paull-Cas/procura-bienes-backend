package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.client.UserClient;
import com.microservice.comprasycotizaciones.client.response.UsuarioResponse;
import com.microservice.comprasycotizaciones.dto.request.ProcuraRequest;
import com.microservice.comprasycotizaciones.dto.response.OfertaCotizadaResponse;
import com.microservice.comprasycotizaciones.dto.response.ProcuraResponse;
import com.microservice.comprasycotizaciones.entities.Cotizacion;
import com.microservice.comprasycotizaciones.entities.DetalleCotizacion;
import com.microservice.comprasycotizaciones.entities.Procura;
import com.microservice.comprasycotizaciones.mapper.ProcuraMapper;
import com.microservice.comprasycotizaciones.repository.ICotizacionRepository;
import com.microservice.comprasycotizaciones.repository.IDetalleCompraRepository;
import com.microservice.comprasycotizaciones.repository.IDetalleCotizacionRepository;
import com.microservice.comprasycotizaciones.repository.IProcuraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProcuraServiceImpl implements IProcuraService {

    @Autowired
    IProcuraRepository procuraRepository;

    @Autowired
    ICotizacionRepository cotizacionRepository;

    @Autowired
    IDetalleCotizacionRepository detalleCotizacionRepository;

    @Autowired
    IDetalleCompraRepository detalleCompraRepository;

    @Autowired
    UserClient userClient;


    @Override
    public void save(ProcuraRequest procuraRequest) {
        procuraRepository.save(ProcuraMapper.toEntity(procuraRequest));
    }

    @Override
    public List<ProcuraResponse> getAll(Long idUsuario) {
        List<Procura> procuras = FiltroPorPermisos.getProcuras(StreamSupport
                .stream(procuraRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()), idUsuario);
        return ProcuraMapper.toListResponse(procuras);
    }

    @Override
    public List<ProcuraResponse> getAllNoCotizadas(Long idUsuario) {
        List<Procura> procuras = FiltroPorPermisos.getProcuras(StreamSupport
                .stream(procuraRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()), idUsuario);

        Set<Long> procurasConCotizacion = FiltroPorPermisos.getCotizaciones(StreamSupport
                        .stream(cotizacionRepository.findCotizacionByIdUsuario(idUsuario).spliterator(), false)
                        .collect(Collectors.toList()), idUsuario)
                .stream()
                .map(cotizacion -> cotizacion.getProcura().getIdProcura())
                .collect(Collectors.toSet());

        List<Procura> procurasSinCotizar = procuras.stream()
                .filter(procura -> !procurasConCotizacion.contains(procura.getIdProcura()))
                .collect(Collectors.toList());

        return ProcuraMapper.toListResponse(procurasSinCotizar);
    }

    @Override
    public List<OfertaCotizadaResponse> getComparativo(Long idProcura) {
        // Ofertas de todos los proveedores que fueron aceptadas técnicamente para esta procura
        List<DetalleCotizacion> detalles = detalleCotizacionRepository
                .findByCotizacion_Procura_IdProcuraAndEstado(idProcura, "Aceptado");

        // Ítems (detalle_requerimiento) que YA fueron incluidos en alguna orden de compra de esta
        // procura: no deben poder volver a comprarse, así que se excluyen del comparativo.
        Set<Long> itemsYaComprados = detalleCompraRepository
                .findByCompra_Cotizacion_Procura_IdProcura(idProcura)
                .stream()
                .map(dc -> dc.getDetalleCotizacion().getIdDetalleRequerimiento())
                .collect(Collectors.toSet());

        detalles = detalles.stream()
                .filter(detalle -> !itemsYaComprados.contains(detalle.getIdDetalleRequerimiento()))
                .collect(Collectors.toList());

        List<Long> usuariosIds = detalles.stream()
                .map(detalle -> detalle.getCotizacion().getIdUsuario())
                .distinct()
                .collect(Collectors.toList());

        List<UsuarioResponse> usuarios = usuariosIds.isEmpty()
                ? List.of()
                : userClient.findAllUsuarios(usuariosIds);

        return detalles.stream()
                .map(detalle -> {
                    Cotizacion cotizacion = detalle.getCotizacion();
                    String proveedor = usuarios.stream()
                            .filter(u -> u.getIdUsuario().equals(cotizacion.getIdUsuario()))
                            .findFirst()
                            .map(UsuarioResponse::getNombreUsuario)
                            .orElse(null);
                    return OfertaCotizadaResponse.builder()
                            .idDetalleCotizacion(detalle.getIdDetalleCotizacion())
                            .idCotizacion(cotizacion.getIdCotizacion())
                            .codigoCotizacion(cotizacion.getCodigoCotizacion())
                            .idUsuario(cotizacion.getIdUsuario())
                            .proveedor(proveedor)
                            .idDetalleRequerimiento(detalle.getIdDetalleRequerimiento())
                            .cantidad(detalle.getCantidad())
                            .precioUnitario(detalle.getPrecioUnitario())
                            .estado(detalle.getEstado())
                            .moneda(cotizacion.getMoneda())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProcuraResponse getById(Long id) {
        Procura procura = procuraRepository.findById(id).orElse(null);
        return ProcuraMapper.toResponse(procura);
    }

    @Override
    public void update(ProcuraRequest procuraRequest, Long idProcura) {
        if (procuraRepository.findById(idProcura).isPresent()) {
            Procura procuraupdate = ProcuraMapper.toEntity(procuraRequest);
            procuraupdate.setIdProcura(idProcura);
            procuraRepository.save(procuraupdate);
        }
        else throw new RuntimeException("Procura no encontrada");
    }

    @Override
    public void delete(Long id) {
        if (procuraRepository.existsById(id)) procuraRepository.deleteById(id);
        else throw new RuntimeException("Procura no encontrada");
    }
}
