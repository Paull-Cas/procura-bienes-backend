package com.microservice.comprasycotizaciones.service;

import com.microservice.comprasycotizaciones.dto.request.CompraConBienesRequest;
import com.microservice.comprasycotizaciones.dto.request.CompraRequest;
import com.microservice.comprasycotizaciones.dto.request.DetalleCompraRequest;
import com.microservice.comprasycotizaciones.dto.response.CompraResponse;
import com.microservice.comprasycotizaciones.entities.Compra;
import com.microservice.comprasycotizaciones.entities.DetalleCompra;
import com.microservice.comprasycotizaciones.entities.Cotizacion;
import com.microservice.comprasycotizaciones.entities.Procura;
import com.microservice.comprasycotizaciones.mapper.CompraMapper;
import com.microservice.comprasycotizaciones.mapper.DetalleCompraMapper;
import com.microservice.comprasycotizaciones.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompraServiceImpl implements ICompraService {

    @Autowired
    ICotizacionRepository cotizacionRepository;

    @Autowired
    IProcuraRepository procuraRepository;

    @Autowired
    ICompraRepository compraRepository;

    @Autowired
    IDetalleCompraRepository detalleCompraRepository;

    @Autowired
    IDetalleCotizacionRepository detalleCotizacionRepository;

    @Transactional
    @Override
    public String save(CompraConBienesRequest compraRequest) {
        Cotizacion cotizacion = cotizacionRepository.getCotizacionByIdCotizacion(compraRequest.getCompra().getIdCotizacion());
        Procura procura = cotizacion.getProcura();
        Compra compra = CompraMapper.toEntity(compraRequest.getCompra(), cotizacion);

        Long numeroSecuencial = compraRepository.countByCotizacion_Procura_IdProcura(procura.getIdProcura()) + 1;
        String titulo = GenerarTitulo.toCompra(procura.getCodigoProcura(), numeroSecuencial);
        compra.setCodigoOrdenCompra(titulo);
        compra.setEstado("Emitida");

        Compra compraGuardada = compraRepository.save(compra);
        guardarDetalles(compraRequest.getBienesComprados(), compraGuardada);

        return titulo;
    }

    private void guardarDetalles(List<DetalleCompraRequest> detalles, Compra compra) {
        List<DetalleCompra> entidades = detalles.stream()
                .map(dto -> DetalleCompraMapper.toEntity(dto, compra, detalleCotizacionRepository.getByIdDetalleCotizacion(dto.getIdDetalleCotizacion())))
                .collect(Collectors.toList());
        detalleCompraRepository.saveAll(entidades);
    }

    @Override
    public List<CompraResponse> getAll(Long idUsuario) {
        List<Compra> compras = FiltroPorPermisos.getCompras(StreamSupport
                .stream(compraRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()), idUsuario);
        return CompraMapper.toListResponse(compras);
    }

    @Override
    public List<CompraResponse> getByProveedor(Long idUsuario) {
        List<Compra> compras = compraRepository.findByCotizacion_IdUsuario(idUsuario);
        return CompraMapper.toListResponse(compras);
    }

    @Override
    public CompraResponse getById(Long id) {
        Compra compra = compraRepository.findById(id).orElse(null);
        return CompraMapper.toResponse(compra);
    }

    @Transactional
    @Override
    public void aceptar(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));
        compra.setEstado("Aceptada");
        compraRepository.save(compra);
    }

    @Override
    public void update(CompraRequest compraRequest, Long id) {
        if (compraRepository.existsById(id)) {
            Cotizacion cotizacion = cotizacionRepository.findById(compraRequest.getIdCotizacion()).get();
            Compra uCompra = CompraMapper.toEntity(compraRequest, cotizacion);
            uCompra.setIdCompra(id);
            compraRepository.save(uCompra);
        }
        else throw new RuntimeException("Compra no encontrada");
    }

    @Override
    public void delete(Long id) {
        if (compraRepository.existsById(id)) compraRepository.deleteById(id);
        else throw new RuntimeException("Compra no encontrada");
    }
}
